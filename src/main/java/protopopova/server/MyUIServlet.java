package protopopova.server;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import org.hibernate.Session;
import protopopova.model.BookEntity;
import protopopova.view.MyUI;

import javax.servlet.annotation.WebServlet;
import java.util.LinkedList;
import java.util.List;

@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
public class MyUIServlet extends VaadinServlet {

    private static Session session;

    public static List<BookEntity> doSelect() {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<BookEntity> list = session.createQuery("from BookEntity").list();
        session.close();
        return  list;
    }
    public static void doDelete(BookEntity entity) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        BookEntity attachedEntity = session.get(BookEntity.class, entity.getId());
        session.delete(attachedEntity);
        session.getTransaction().commit();
        session.close();
    }
    public static BookEntity doInsert(BookEntity entity) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
        return entity;
    }
    public static BookEntity readBook(BookEntity entity) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        entity.setReadAlready(true);
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return entity;
    }
    public static BookEntity update(BookEntity entity) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        BookEntity attachedEntity = session.get(BookEntity.class, entity.getId());
        attachedEntity.setTitle(entity.getTitle());
        attachedEntity.setDescription(entity.getDescription());
        attachedEntity.setIsbn(entity.getIsbn());
        attachedEntity.setPrintYear(entity.getPrintYear());
        attachedEntity.setReadAlready(false);
        session.update(attachedEntity);
        session.getTransaction().commit();
        session.close();
        return attachedEntity;
    }
    public static BookEntity getById(int id) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        BookEntity attachedEntity = session.get(BookEntity.class, id);
        session.close();
        return attachedEntity;
    }

    public static Session getSession() {
        return session;
    }



}
