export default{
  template: `
  <div>
  <h2>코로나 19 예방접종센터 상세 페이지</h2>
  <table border="1">
    <tbody>
      <tr>
        <td align="center">센터명</td>
        <td>{{center.centerName}}</td>
      </tr>
      <tr>
        <td align="center">센터유형</td>
        <td>{{center.centerType}}</td>
      </tr>
      <tr>
        <td align="center">주소</td>
        <td>{{center.address}}</td>
      </tr>
      <tr>
        <td align="center">센터시설명</td>
        <td>{{center.org}}</td>
      </tr>
      <tr>
        <td align="center">전화번호</td>
        <td>{{center.phoneNumber}}</td>
      </tr>
      <tr>
        <td align="center">생성일</td>
        <td>{{center.createdAt}}</td>
      </tr>
      <tr>
        <td align="center">수정일</td>
        <td>{{center.updatedAt}}</td>
      </tr>
    </tbody>
  </table>
  </div>
  `,
  created(){
    console.log(this.$route.params);
    let id=this.$route.params.id;
    this.perPage=this.$route.params.perPage;

    const URL="https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage="+this.perPage+"&serviceKey=pK%2B5v%2BJKLaoeG%2FpUU1QVku5WJ7JKwySWiCvRbpo4StK4pBIuKHVrxEuibjXNZGTBVqhZeeoS8fzCbBRy7uCOCg%3D%3D";
    axios.get(URL).then((response)=>{
      console.log(response);
      this.centerList=response.data;
      this.center=this.centerList.data[id];
      console.log(this.centerList.data[id]);
    });

  },
  data(){
    return{
      center:{},
      centerList,
      perPage:10
    }
  }, 
}