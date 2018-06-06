package protopopova.view;

import com.vaadin.data.SelectionModel;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.SingleSelectionModelImpl;
import protopopova.model.BookEntity;
import protopopova.server.MyUIServlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Left extends VerticalLayout {

    private int startIndex = 0;
    private int step = 10;
    private int endIndex = step;
    private int countPages = 1;
    private int totalCount;
    private Grid<BookEntity> bookTable;
    private List<BookEntity> allBooks = new ArrayList<>();
    private final Label pages = new Label();
    private final Button next;
    private final Button prew;
    private TextField search;
    private Button newBook;
    private List<BookEntity> filteredList;

    public Button getNewBook() {
        return newBook;
    }

    public TextField getSearch() {
        return search;
    }

    public Left() {

        HorizontalLayout searchPanel = new HorizontalLayout();
        searchPanel.setWidth(100, Unit.PERCENTAGE);
        search = new TextField();
        search.setPlaceholder("Filter books...");
        search.setWidth(80, Unit.PERCENTAGE);
        newBook = new Button("add new Book");


        searchPanel.addComponent(search);
        searchPanel.addComponent(newBook);

        addComponent(searchPanel);

        bookTable = new Grid<>();
//        allBooks = MyUIServlet.doSelect();
        bookTable = new Grid<>();
        bookTable.addColumn(BookEntity::getAuthor).setCaption("author");
        bookTable.addColumn(BookEntity::getTitle).setCaption("title");
        bookTable.addColumn(BookEntity::getPrintYear).setCaption("year");
        bookTable.addColumn(BookEntity::getIsbn).setCaption("isbn");
        bookTable.setSelectionMode(Grid.SelectionMode.SINGLE);
        allBooks = MyUIServlet.doSelect();
        filteredList = allBooks;
        countPagesSize();
        System.out.println(totalCount);
        DataProvider<BookEntity, ?> dataProvider = new ListDataProvider<>(allBooks.subList(0, 10));
        bookTable.setDataProvider(dataProvider);
        bookTable.setWidth("100%");
        bookTable.setFooterVisible(true);
//        bookTable.setItems(allBooks);
        bookTable.setHeightByRows(10);

        HorizontalLayout paging = new HorizontalLayout();
        next = new Button("Next");
        prew = new Button("Prew");
        pages.setValue("page "+ countPages+" from "+totalCount);
        paging.addComponent(prew);
        paging.addComponent(pages);
        paging.addComponent(next);
        addComponent(bookTable);
        addComponent(paging);

        setWidth("100%");

        next.addClickListener(listner -> {
            if (countPages< totalCount) {
                startIndex = countPages*step;
                endIndex = startIndex+step;
                if (endIndex>allBooks.size()) {
                    endIndex = allBooks.size();
                }
                bookTable.setItems(allBooks.subList(startIndex, endIndex));
                countPages++;
                pages.setValue("page "+ countPages+" from "+totalCount);

            }
        });
        prew.addClickListener(listner -> {
            if (countPages>1) {
                startIndex = (countPages-2)*step;
                endIndex = startIndex+step;

                bookTable.setItems(allBooks.subList(startIndex, endIndex));
                countPages--;
                pages.setValue("page "+ countPages+" from "+totalCount);

            }
        });




    }


    private void countPagesSize() {
        int oldTotal = totalCount;
        if (filteredList.size()%step==0) {
            totalCount = filteredList.size()/step;
        } else {
            totalCount = (filteredList.size()/step)+1;
        }
        if (totalCount<oldTotal) {
            if (countPages>1) {
                countPages--;
            }
        }

        pages.setValue("page "+ countPages+" from "+totalCount);
    }

    public void setItemsData(List<BookEntity> items) {
        this.allBooks = items;
        if (endIndex>allBooks.size() || endIndex%step!=0) {
            endIndex = allBooks.size();
        }
        if (startIndex==endIndex) {
            prew.click();
        } else {
            bookTable.setItems(items.subList(startIndex, endIndex));
        }
        countPagesSize();

    }
    public void setFilteredData(List<BookEntity> items) {
        this.filteredList = items;
        countPages = 1;
        startIndex = 0;
        endIndex = step;
        if (endIndex>filteredList.size() || endIndex%step!=0) {
            endIndex = filteredList.size();
        }

            bookTable.setItems(items.subList(startIndex, endIndex));

        countPagesSize();

    }


    public Grid<BookEntity> getBookTable() {
        return bookTable;
    }

    public void refresh(String value) {
        List<BookEntity> filteredList = new ArrayList<>();
        if (value==null || value=="") {
            filteredList = allBooks;
        } else {

            for (BookEntity book: allBooks) {
                if (book.getTitle().toLowerCase().contains(value.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(value.toLowerCase()) ||
                book.getIsbn().toLowerCase().contains(value.toLowerCase())) {
                    filteredList.add(book);
                }
            }


        }
       setFilteredData(filteredList);

    }
}
