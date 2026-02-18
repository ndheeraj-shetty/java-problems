import java.util.ArrayList;
public class ArrayListOfBooks{
    public static void main(String[] args){
        Book book1 = new Book();
        Book book2 = null;
        Book book3 = null;
        Book book4 = null;
        Book book5 = null;
        Book book6 = null;
        Book book8 = null;
        Book book9 = null;
        Book book10 = null;
        Book book11 = null;
        
        try {
            book2 = new Book("artificial intellgence", 10.99);
        } catch (InvalidPriceException e) {
            System.out.println("Error creating book: " + e.getMessage());
        }
        
        
        try{
            book6 = new Book("harry potter", 15.99);
            book3 = new Book(book2);
            book4 = new Book("sachin tendlukar", 8.99, "978-0-987654-32-1", "Non-Fiction", "Lilly the lonely", 2021);
            book5 = new Book("rich dad poor dad", 9.99, "978-0-567890-12-3", "Fiction", "Adam the author", 2019);
            book8 = new Book("winner", 11.99, "978-0-246810-12-4", "Fiction", "Violet the writer", 2022);
            book9 = new Book("king of the jungle", 7.99, "978-0-135791-24-6", "Non-Fiction", "Charlie the chef", 2018);
            book10 = new Book("the last book", 13.99, "978-0-246801-35-7", "Fiction", "Penny the pastry chef", 2023);
        } catch (InvalidPriceException e) {
            System.out.println("Error creating book: " + e.getMessage());
        }

        try {
            book11 = new Book("art of the deal", -5.99);
        } catch (InvalidPriceException e) {
            System.out.println("Error creating book: " + e.getMessage());
        }

        // creating object using copy constructor 

        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);
        bookList.add(book6);
        bookList.add(book8);
        bookList.add(book9);
        bookList.add(book10);
        bookList.add(book11);

        // printing details of each book in the list

        System.out.println("Book details:\n");
        for (Book book : bookList) {
            if (book == null) continue;
            System.out.println("\n================================\n");
            System.out.println("Title: " + book.title);
            System.out.println("Price: $" + book.price);
            System.out.println("ISBN: " + book.ISBN);
            System.out.println("Genre: " + book.genre);
            System.out.println("Author: " + book.author);
            System.out.println("Year Published: " + book.yearPublished);
            System.out.println("\n================================\n");
        }

        // printing average price of books in the list
        double totalPrice = 0.0;
        for (Book book : bookList) {
            if (book == null) continue;
            totalPrice += book.price;
        }
        double averagePrice = totalPrice / bookList.size(); 
        System.out.println("Average Price of Books: $" + averagePrice);


        // printing all the books of 'Non-fiction' genre

        System.out.println("\nBooks in the 'Non-Fiction' genre:\n");
        for (Book book : bookList) {
            if (book == null) continue;
            if (book.genre != null && book.genre.equals("Non-Fiction")) {
                System.out.println("\n================================\n");
                System.out.println("Title: " + book.title);
                System.out.println("Price: $" + book.price);
                System.out.println("ISBN: " + book.ISBN);
                System.out.println("Genre: " + book.genre);
                System.out.println("Author: " + book.author);
                System.out.println("Year Published: " + book.yearPublished);
                System.out.println("\n================================\n");
            }
        }
    }
}
