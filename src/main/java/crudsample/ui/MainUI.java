package crudsample.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import crudsample.domain.User;
import crudsample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.vaadin.viritin.button.ConfirmButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.MValueChangeEvent;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;
import java.util.Locale;

/**
 * Created by Cepro on 15.07.2016.
 */
@Title("CRUD sample")
@Theme("crudsample")
@SpringUI
public class MainUI extends UI {

    private final UserService userService;
    private MTable<User> userTable;
    private Button addUserBtn;
    private Button editUserBtn;
    private Button deleteUserBtn;
    private TextField filterByNameFld;
    private UserForm userForm;

    @Autowired
    public MainUI(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void init(VaadinRequest request) {

        // JavaRush Logo
        Link javaRushLogo = new Link(null, new ExternalResource("http://javarush.ru"));
        javaRushLogo.setTargetName("_new");
        javaRushLogo.setDescription("JavaRush — онлайн-курс обучения программированию на Java");
        //javaRushLogo.setIcon(new ExternalResource("http://javarush.ru/images/main.2/javarush_logo.png"));
        //javaRushLogo.setIcon(new FileResource(new File(VaadinService.getCurrent()
        //        .getBaseDirectory().getAbsolutePath() + "/crudsample/javarush_logo.png")));
        javaRushLogo.setIcon(new ThemeResource("javarush_logo.png"));

        // Welcome text
        RichText welcomeText = new RichText().withMarkDownResource("/welcome.md");

        // Table with User data
        userTable = new MTable<>(User.class)
                .withProperties("id", "name", "age", "isAdmin", "createDate")
                .withColumnHeaders("ID", "Имя", "Возраст", "Роль", "Дата регистрации")
                .setSortableProperties("id", "name", "age", "isAdmin", "createDate")
                .withFullWidth();
        userTable.setSelectable(true);
        userTable.addMValueChangeListener(this::adjustControls);

        userTable.addItemClickListener(this::edit);

        // Add new User Button
        addUserBtn = new MButton(FontAwesome.PLUS, this::add);
        addUserBtn.setClickShortcut(ShortcutAction.KeyCode.INSERT);
        addUserBtn.setDescription("Добавить пользователя (Insert)");

        // Edit User Button
        editUserBtn = new MButton(FontAwesome.PENCIL_SQUARE_O, this::edit);
        editUserBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER, ShortcutAction.ModifierKey.ALT);
        editUserBtn.setDescription("Редактировать данные (Alt-Enter)");

        deleteUserBtn = new ConfirmButton(FontAwesome.TRASH_O, "Удаление", this::delete)
                .withI18NCaption("Да", "Отмена");
        ((ConfirmButton) deleteUserBtn).setConfirmWindowCaption("Удалить пользователя?");
        deleteUserBtn.setClickShortcut(ShortcutAction.KeyCode.DELETE, ShortcutAction.ModifierKey.ALT);
        deleteUserBtn.setDescription("Удалить пользователя (Alt-Delete)");

        filterByNameFld = new MTextField();
        filterByNameFld.setInputPrompt("Фильтр по имени (Alt-F)");
        filterByNameFld.setWidth("200px");
        filterByNameFld.addTextChangeListener(event -> updateUI(event.getText()));
        filterByNameFld.addShortcutListener(
                new AbstractField.FocusShortcut(
                        filterByNameFld, ShortcutAction.KeyCode.F, ShortcutAction.ModifierKey.ALT));

        // Edit controls layout
        MHorizontalLayout editLayout = new MHorizontalLayout(filterByNameFld, addUserBtn, editUserBtn, deleteUserBtn);

        // Main layout of UI
        MVerticalLayout mainLayout = new MVerticalLayout(
                javaRushLogo,
                welcomeText,
                editLayout,
                userTable).expand(userTable);

        setContent(mainLayout);

        Locale locale = new Locale("ru");
        this.setLocale(locale);
        this.getSession().setLocale(locale);

        addShortcutListener(new ShortcutListener("Escape", ShortcutAction.KeyCode.ESCAPE, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                updateUI();
                userTable.focus();
            }
        });

        userTable.focus();
        updateUI();
    }

    // Add new user listener
    private void add(Button.ClickEvent event) {
        User user = new User();
        openInUserForm(user);
    }

    // Edit user listener for double clicking in userTable
    private void edit(ItemClickEvent event) {
        if (event.isDoubleClick()) {
            openInUserForm((User) event.getItemId());
        }
    }

    // Edit user listener
    private void edit(Button.ClickEvent event) {
        openInUserForm(userTable.getValue());
    }

    // Delete user listener
    private void delete(Button.ClickEvent event) {
        userService.delete(userTable.getValue());
        userTable.setValue(null);
        updateUI();
    }

    // Open User Form
    private void openInUserForm(User user) {
        userForm = new UserForm(user);
        userForm.openInModalPopup();
        userForm.setSavedHandler(this::save);
        userForm.setResetHandler(this::cancel);
    }

    // User Form save handler
    private void save(User user) {
        userService.save(user);
        userTable.select(user.getId());
        updateUI(user.getId());
        userForm.closePopup();
    }

    // User Form cancel handler
    private void cancel(User user) {
        updateUI(filterByNameFld.getValue());
        userForm.closePopup();
    }

    // Adjust controls listener
    private void adjustControls(MValueChangeEvent<User> mValueChangeEvent) {
        adjustControls();
    }

    // Adjust controls method
    private void adjustControls() {
        boolean hasSelection = userTable.getValue() != null;
        editUserBtn.setEnabled(hasSelection);
        deleteUserBtn.setEnabled(hasSelection);
    }

    // Update UI with all users
    private void updateUI() {
        updateUI(null);
    }

    // Update UI with one user by ID
    private void updateUI(int id) {
        List<User> userList = userService.getAllById(id);
        userTable.setBeans(userList);
        if (!userList.isEmpty())
            filterByNameFld.setValue(userList.get(0).getName());

        adjustControls();
    }

    // Update UI with filter
    private void updateUI(String filterName) {
        if (!StringUtils.isEmpty(filterName)) {
            userTable.setBeans(userService.getAllByName(filterName));
        } else {
            userTable.setBeans(userService.getAll());
            filterByNameFld.setValue("");
        }
        adjustControls();
    }
}
