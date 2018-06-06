package protopopova.view;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import protopopova.model.BookEntity;
import protopopova.server.MyUIServlet;

import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {


    public MyUI() {
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
       setContent(new MainLayout());
    }



//    final S layout = new VerticalLayout();
//    Grid<BookEntity> bookTable = new Grid<>();
////        bookTable.addColumn(BookEntity::getId).setCaption("id");
//        bookTable.addColumn(BookEntity::getAuthor).setCaption("author");
//        bookTable.addColumn(BookEntity::getTitle).setCaption("title");
//        bookTable.addColumn(BookEntity::getPrintYear).setCaption("year");
//        bookTable.addColumn(BookEntity::getIsbn).setCaption("isbn");
////        bookTable.addColumn(BookEntity::getDescription).setCaption("description").setWidth(400).setResizable(true);
//
//        bookTable.setItems(servlet.doSelect());
//        bookTable.setWidth("100%");
//        layout.addComponent(bookTable);
//        layout.setWidth("100%");
//    setContent(layout);
//
//    // ContactForm is an example of a custom component class
//    private ContactForm contactForm;
//
//    private LeftPanel left;
//
//    @Override
//    public void attach() {
//        super.attach();
//
//        if (contactForm == null) {
//            Design.read(this);
//            configureComponents();
//        }
//    }


}
