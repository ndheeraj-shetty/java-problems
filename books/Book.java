public class Book {
    public String title;
    public double price;
    public String ISBN;
    public String genre;
    public String author;
    public int yearPublished;

    //default constructor
    public Book(){
        title = "mr bean's book";
        price = 12.99;
        ISBN = "978-0-123456-78-9";
        genre = "Non-Fiction";
        author = "rowan atkinson";
        yearPublished = 2006;
    }

    //parameterized constructor
    public Book(String title, double price) throws InvalidPriceException {
        this.title = title;
        if (price < 0) {
            throw new InvalidPriceException("Invalid price: " + price);
        }
        this.price = price;
    }

    //parameterized constructor with all attributes
public Book(String title, double price, String ISBN, String genre,
            String author, int yearPublished){

    this.title = title;
    this.price = price;
    this.ISBN = ISBN;
    this.genre = genre;
    this.author = author;
    this.yearPublished = yearPublished;
}

    //copy constructor
    public Book(Book book){
        this.title = book.title;
        this.price = book.price;
        this.ISBN = book.ISBN;
        this.genre = book.genre;
        this.author = book.author;
        this.yearPublished = book.yearPublished;}

    

    
}