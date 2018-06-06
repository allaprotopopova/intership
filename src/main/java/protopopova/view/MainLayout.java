package protopopova.view;

import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Notification;
import protopopova.model.BookEntity;
import protopopova.server.MyUIServlet;

public class MainLayout extends HorizontalSplitPanel {
    private Left left;
    private BookForm bookForm;

    public MainLayout() {

        left = new Left();
        bookForm = new BookForm();

        setFirstComponent(left);
        setSecondComponent(bookForm);
        bookForm.setVisible(false);

        left.getBookTable().addItemClickListener(listner -> {
            BookEntity bookEntity = listner.getItem();
            if (!bookForm.isVisible()) {
                bookForm.setVisible(true);
            }
            bookForm.setContactBeingEdited(bookEntity);

        });
        bookForm.getRead().addClickListener(listner -> {

           BookEntity refreshContact = MyUIServlet.readBook(bookForm.getContactBeingEdited());
           left.getBookTable().getDataProvider().refreshItem(refreshContact);
           bookForm.setContactBeingEdited(refreshContact);
            String msg = String.format("Readed '%s, author is %s'.",
                    refreshContact.getTitle(),
                    refreshContact.getAuthor());
            Notification.show(msg, Notification.Type.TRAY_NOTIFICATION);
           left.setItemsData(MyUIServlet.doSelect());
           bookForm.setVisible(false);
        });
        bookForm.getSave().addClickListener(listner -> {
            BookEntity newEntity = new BookEntity();
            if (bookForm.hasBookEntity()) {
                newEntity.setId(bookForm.getContactBeingEdited().getId());
            }
            newEntity.setAuthor(bookForm.getAuthor().getValue());
            newEntity.setTitle(bookForm.getTitle().getValue());
            newEntity.setPrintYear(Integer.valueOf(bookForm.getYear().getValue()));
            newEntity.setIsbn(bookForm.getIsbn().getValue());
            newEntity.setDescription(bookForm.getBookDescription().getValue());
            newEntity.setReadAlready(false);
            BookEntity refreshContact;
            if (bookForm.hasBookEntity()) {
                refreshContact = MyUIServlet.update(newEntity);
                left.getBookTable().getDataProvider().refreshItem(refreshContact);
            } else {
                if (newEntity.getAuthor()=="") {
                    Notification.show("Error save! Book hasn't author!", Notification.Type.TRAY_NOTIFICATION);
                    return;
                }
                refreshContact = MyUIServlet.doInsert(newEntity);
            }
            bookForm.setContactBeingEdited(refreshContact);
            String msg = "Saved successful!";
            Notification.show(msg, Notification.Type.TRAY_NOTIFICATION);
            left.setItemsData(MyUIServlet.doSelect());
            bookForm.setVisible(false);
        });
        bookForm.getDelete().addClickListener(listner -> {
            BookEntity forDel = bookForm.getContactBeingEdited();
            MyUIServlet.doDelete(forDel);
            bookForm.setContactBeingEdited(null);
            String msg = String.format("Book '%s, author is %s' deleted successful.",
                    forDel.getTitle(),
                    forDel.getAuthor());
            Notification.show(msg, Notification.Type.TRAY_NOTIFICATION);
            left.setItemsData(MyUIServlet.doSelect());
            bookForm.setVisible(false);
        });
        left.getNewBook().addClickListener(listner -> {
            if (!bookForm.isVisible()) {
                bookForm.setVisible(true);
            }
            bookForm.setContactBeingEdited(null);
            bookForm.getSave().setEnabled(true);
            bookForm.getAuthor().setEnabled(true);
        });

        left.getSearch().addValueChangeListener(listner -> {
            left.refresh(listner.getValue());
        });
    }
}
