export default{
    template: `
    <div class="regist">
      <h1 class="underline">SSAFY 도서 정보</h1>
      <div class="regist_form">
        <label for="isbn">도서번호</label>
        <div class="view">{{book.isbn}}</div>
        <label for="title">도서명</label>
        <div class="view">{{book.title}}</div>
        <label for="author">저자</label>
        <div class="view">{{book.author}}</div>
        <label for="price">가격</label>
        <div class="view">{{book.price}}</div>
        <label for="content">설명</label>
        <div class="view">{{book.content}}</div>
        <div style="padding-top: 15px">
          <router-link :to="'./modify?isbn='+book.isbn" class="btn">수정</router-link>
          <router-link :to="'/delete?isbn='+book.isbn" class="btn">삭제</router-link>
          <router-link to="/list" class="btn">목록</router-link>
        </div>
      </div>
    </div>
    `,
    created() {
      axios.get(`http://localhost:9999/vuews/book/${this.$route.query.isbn}`).then(({data})=>{
        this.book=data;
        console.log(data);
      })
    },
    data(){
        return {
          book: {}
        }
    },
}