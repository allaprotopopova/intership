package protopopova.view;

import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import protopopova.model.BookEntity;
import protopopova.server.MyUIServlet;

public class BookForm extends FormLayout {

    private TextField title = new TextField();
    private TextField author = new TextField();
    private TextField isbn = new TextField();
    private TextField year = new TextField();
    private TextArea description = new TextArea();
    private CheckBox readAlready = new CheckBox();
    protected Button save = new Button();
    protected Button read = new Button();
    protected Button delete = new Button();

    private BookEntity contactBeingEdited;

    public BookEntity getContactBeingEdited() {
        return contactBeingEdited;
    }

    public void setContactBeingEdited(BookEntity contactBeingEdited) {
        this.contactBeingEdited = contactBeingEdited;
        if (contactBeingEdited==null) {
           clean();
        } else {
            title.setValue(contactBeingEdited.getTitle());
            author.setValue(contactBeingEdited.getAuthor());
            description.setValue(contactBeingEdited.getDescription());
            isbn.setValue(contactBeingEdited.getIsbn());
            year.setValue(String.valueOf(contactBeingEdited.getPrintYear()));
            readAlready.setValue(contactBeingEdited.getReadAlready());
            read.setEnabled(!contactBeingEdited.getReadAlready());
            save.setEnabled(true);
            delete.setEnabled(true);
            author.setEnabled(false);
        }

    }

    private void clean() {
        title.setValue("");
        author.setValue("");
        description.setValue("");
        isbn.setValue("");
        year.setValue("");
        readAlready.setValue(false);
        read.setEnabled(false);
        delete.setEnabled(false);
        save.setEnabled(false);


    }

    public BookForm() {
        this.setMargin(new MarginInfo(true, true));
       configureComponents();
    }

    private void configureComponents() {
        title.setCaption("Title");
        title.setWidth(400, Unit.PIXELS);
        author.setCaption("Author");
        author.setWidth(400, Unit.PIXELS);
        author.setEnabled(false);
        year.setCaption("Year");
        year.setWidth(400, Unit.PIXELS);
        isbn.setCaption("Isbn");
        isbn.setWidth(400, Unit.PIXELS);
        description.setCaption("Description");
        description.setWidth(400, Unit.PIXELS);
        description.setHeight(400, Unit.PIXELS);
        readAlready.setCaption("Read");
        readAlready.setEnabled(false);
        save.setCaption("Save");
        save.setEnabled(false);
        read.setCaption("Read");
        read.setEnabled(false);
        delete.setCaption("Delete");
        delete.setEnabled(false);
         addComponent(title);
         addComponent(author);
        addComponent(year);
        addComponent(isbn);
        addComponent(description);

        HorizontalLayout btnPanel = new HorizontalLayout();

        btnPanel.addComponent(readAlready);
        btnPanel.addComponent(read);
        btnPanel.addComponent(save);
        btnPanel.addComponent(delete);
        addComponent(btnPanel);


        /*
         * Highlight primary actions.
         *
         * With Vaadin built-in styles you can highlight the primary save button
         * and give it a keyboard shortcut for a better UX.
         */
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        read.setStyleName(ValoTheme.BUTTON_PRIMARY);
        delete.setStyleName(ValoTheme.BUTTON_PRIMARY);




    }
    public  boolean hasBookEntity() {
        if (contactBeingEdited!=null) {
            return true;
        } else {
            return false;
        }
    }


    public Button getDelete() {
        return delete;
    }

    public Button getRead() {
        return read;
    }


    public TextArea getBookDescription() {
        return description;
    }

    public TextField getTitle() {
        return title;
    }

    public TextField getAuthor() {
        return author;
    }

    public TextField getIsbn() {
        return isbn;
    }

    public TextField getYear() {
        return year;
    }


    public Button getSave() {
        return save;
    }
}
