import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15953 {
// Baekjoon 15953. 상금헌터
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input15953.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		int[][] arr=new int[N][2];
		for (int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		
		int[][] prize1={{1,500},{3,300},{6,200},{10,50},{15,30},{21,10}};
		int[][] prize2={{1,512},{3,256},{7,128},{15,64},{31,32}};
		StringBuilder sb=new StringBuilder();
		
		for (int i=0; i<N; i++) {
			int result=0;
			if (arr[i][0]!=0) {
				for (int j=0; j<6; j++) {
					if (prize1[j][0]>=arr[i][0]) {
						result+=prize1[j][1]*10000;
						break;
					}
				}
			}
			if (arr[i][1]!=0) {
				for (int j=0; j<5; j++) {
					if (prize2[j][0]>=arr[i][1]) {
						result+=prize2[j][1]*10000;
						break;
					}
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
		br.close();

	}

}
