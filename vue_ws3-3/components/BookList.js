export default{
    template: "#BookList",
    data(){
        return {
          books: []
        }
      },
      created() {
        const books = localStorage.getItem("books");
        let newBook={
          books: []
        }
        if (books){
          newBook.books=JSON.parse(books);
        }

        newBook.books.sort((a,b)=>{
          return a.price-b.price;
        })

        this.books=newBook.books;
      },
      methods: {
        mvRegister(){
          location.href="../create.html";
        }
      },
}