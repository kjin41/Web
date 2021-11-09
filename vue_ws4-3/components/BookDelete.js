export default{
    template: `
    <div>
      <h1>SSAFY 도서 삭제</h1>
      <div>삭제중...</div>
    </div>
    `,
    created() {
      axios.delete(`http://localhost:9999/vuews/book/${this.$route.query.isbn}`).then(({data})=>{
        let msg="삭제시 문제 발생";
        if (data==="success"){
          msg="삭제 완료";
        }
        alert(msg);
        this.$router.push("/list");
      })
    }
}