package crudsample.ui;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import crudsample.domain.Role;
import crudsample.domain.User;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Created by Cepro on 16.07.2016.
 */
class UserForm extends AbstractForm<User> {

    private TextField name;
    private TextField age;
    private ComboBox isAdmin;

    UserForm(User user) {

        setModalWindowTitle("Пользователь");
        setCaption("Введите данные:");
        setSaveCaption("Сохранить");
        setDeleteCaption("Удалить");
        setCancelCaption("Отмена");

        name = new MTextField("Имя");
        name.setInputPrompt("От 2-х до 25 симв.");

        age = new MTextField("Возраст");
        age.setInputPrompt("от 16 до 65 лет");
        age.setConverter(Integer.class);

        isAdmin = new ComboBox("Роль");
        isAdmin.addItems(Role.USER, Role.ADMIN);
        isAdmin.setNullSelectionAllowed(false);
        isAdmin.setValue(Role.USER);

        setSizeUndefined();
        setEntity(user);
    }

    @Override
    protected Component createContent() {
        FormLayout userForm = new MFormLayout(new MLabel("Введите данные:"), name, age, isAdmin).withWidth("");
        return new MVerticalLayout(userForm, getToolbar()).withWidth("");
    }
}
