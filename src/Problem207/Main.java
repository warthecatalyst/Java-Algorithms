package Problem207;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

    }
}


class Solution{
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new int[numCourses];
        edges = new ArrayList<List<Integer>>();
        for(int i=0;i<numCourses;i++){
            edges.add(new ArrayList<Integer>());
        }
        for(int[] info:prerequisites){
            edges.get(info[1]).add(info[0]);
        }
        for(int i=0;i<numCourses&&valid;i++){
            dfs(i);
        }
        return valid;
    }
    public void dfs(int u){
        visited[u] = 1;
        for(int v:edges.get(u)){
            if(visited[v]==0){
                dfs(v);
                if(!valid)
                    return;
            }else if(visited[v]==1){
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }
}