export default{
    template: "#BookDelete",
    created() {
        const params=new URL(document.location).searchParams;
        let param=params.get("isbn");
        let books=JSON.parse(localStorage.getItem("books"));
        
        books=books.filter(book=>{
          return book.isbn!=param;
        });

        localStorage.setItem("books", JSON.stringify(books));
        alert("도서 삭제 성공");
        location.href="../list.html";
      }
}