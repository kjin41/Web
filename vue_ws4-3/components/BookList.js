export default{
    template: `
    <div>
      <div style="text-align: right">
        <button @click="mvRegister()" class="btn">도서 등록</button>
      </div>
      <h1 class="underline">도서 목록</h1>
      <div v-if="books.length">
        <table id="book-list">
          <colgroup>
            <col style="width: 5%" />
            <col style="width: 20%" />
            <col style="width: 40%" />
            <col style="width: 20%" />
            <col style="width: 15%" />
          </colgroup>
          <thead>
            <tr>
              <th>번호</th>
              <th>ISBN</th>
              <th>제목</th>
              <th>저자</th>
              <th>가격</th>
            </tr>
          </thead>
          <tbody>
            <tr class="table-info" v-for="(book, index) in books" :key="index">
              <td>{{index+1}}</td>
              <td>{{book.isbn}}</td>
              <td><router-link :to="'view?isbn='+book.isbn">{{book.title }}</router-link></td>
              <td>{{book.author }}</td>
              <td>{{book.price }}원</td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="text-center" v-if="!books.length">도서 목록이 없습니다.</div>
    </div>
    `,
    data(){
        return {
          books: []
        }
      },
      created() {
        axios.get("http://localhost:9999/vuews/book").then(({data})=>{
          this.books=data;
        })
      },
      methods: {
        mvRegister(){
          // location.href="/create"; // 뭔가 잘 안됨.
          this.$router.push("/create");
        }
      },
}