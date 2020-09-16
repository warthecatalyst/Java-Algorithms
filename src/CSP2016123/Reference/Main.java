package CSP2016123.Reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main{
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int p = scanner.nextInt();//权限数量
        scanner.nextLine();
        Map<Integer,Privilege> privileges = new HashMap<>();//保存权限
        for(int i = 0; i < p;i++) {
            String str = scanner.nextLine();
            Privilege privilege = new Privilege();
            if(str.contains(":")) {
                String[] strs = str.split(":");
                privilege.name = strs[0];
                privilege.range = Integer.parseInt(strs[1]);
            } else {
                privilege.name = str;
            }
            privileges.put(privilege.name.hashCode(), privilege);
        }

        //角色处理
        int r = scanner.nextInt();//角色数量
        Map<Integer,Role> roles = new HashMap<>();
        scanner.nextLine();
        for(int i = 0; i < r; i++) {
            // rols s p1 p2 p3...
            String roleStr = scanner.nextLine();
            Role role = new Role();
            String[] strs = roleStr.split(" ");
            role.roleName = strs[0];
            role.privilegeNum = Integer.parseInt(strs[1]);
            for(int j = 2;j < strs.length;j++) {
                RolePrivilege rp = new RolePrivilege();
                if(strs[j].contains(":")) {
                    String[] strss = strs[j].split(":");
                    RolePrivilege existP = role.privileges.get(strss[0].hashCode());
                    //如果已经存在带某个等级的权限那么更新为最大等级的权限就行了
                    if(existP!=null) {
                        int cc = Integer.parseInt(strss[1]);
                        existP.level = cc > existP.level? cc:existP.level;
                        continue;
                    }
                    rp.privilege = privileges.get(strss[0].hashCode());
                    rp.level = Integer.parseInt(strss[1]);
                } else {
                    rp.privilege = privileges.get(strs[j].hashCode());
                    rp.level = -1;
                }
                role.privileges.put(rp.privilege.name.hashCode(),rp);
            }
            roles.put(role.roleName.hashCode(),role);
        }
        Role role = ((Role)(roles.values().toArray()[0]));
        RolePrivilege ppp = (RolePrivilege) role.privileges.values().toArray()[0];
        //用户处理
        int u = scanner.nextInt();
        scanner.nextLine();
        Map<Integer,User> users = new HashMap<>();
        for(int i = 0; i < u;i++) {
            String userStr = scanner.nextLine();
            User user = new User();
            String[] strs = userStr.split(" ");
            user.userName = strs[0];
            user.roleNum = Integer.parseInt(strs[1]);
            for(int j = 2; j < strs.length;j++) {
                Role ro = roles.get(strs[j].hashCode());
                user.roles.put(ro.roleName.hashCode(),ro);
            }
            users.put(user.userName.hashCode(), user);
        }
        //查询处理
        int q = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0;i < q;i++) {
            String query = scanner.nextLine();
            String[] strs = query.split(" ");
            String username = strs[0];
            User user = users.get(username.hashCode());
            if(user == null) {//用户不存在
                System.out.println(false);
                continue;
            }
            Privilege privilege = new Privilege();
            if(strs[1].contains(":")) {
                String[] strss = strs[1].split(":");
                privilege.name = strss[0];
                privilege.range = Integer.parseInt(strss[1]);
            } else {
                privilege.name = strs[1];
            }
            Object obj = user.hasPrivilege(privilege);
            System.out.println(obj);
        }
    }

    //权限类
    public static class Privilege{
        public String name;
        public Integer range = -1;//默认-1表示不分等级 权限范围
        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }
    //角色权限
    public static class RolePrivilege{
        public Privilege privilege;
        public int level = -1;
    }

    //角色类
    public static class Role{
        public String roleName;
        public int privilegeNum = 0;
        public Map<Integer,RolePrivilege> privileges = new HashMap<>();
        public void hasPrivilege(Privilege privilege, List<Object> result) {
            RolePrivilege rp = privileges.get(privilege.name.hashCode());
            //根本没有该权限
            if(rp == null) {
                result.add(false);
                return ;
            }
            //有权限但是不含等级 没有考虑如果权限本身不含等级但是查询的时候出现等级的情况
            if(rp.level == -1) {
                if(privilege.range!=-1) {//如果权限没有等级 但是查询的时候给出了等级
                    result.add(false);
                    return;
                }
                result.add(true);
                return;
            }



            //权限含等级 但是查询的时候没有写等级

            if(privilege.range==-1) {

                result.add(rp.level);

                return;

            } else if(privilege.range <=rp.level){ //权限含等级 并且查询给出等级 那么满足条件就认为是有权限的

                result.add(true);

                return;

            }

            result.add(false);

        }

    }

    public static class User{

        public String userName;

        public int roleNum;

        public Map<Integer,Role> roles = new HashMap<>();

        //用户是否有权限

        public Object hasPrivilege(Privilege privilege) {

            Set<Integer> keys = roles.keySet();

            //出现问题就出在这里 如果已经查询到的obj不为null，继续查询可能覆盖以前的结果

            //必须角色查询完才知道是不是具有该权限，除了其中的结果有返回false的

            List<Object> result = new ArrayList<>();

            for(Integer integer : keys) {

                Role role = roles.get(integer);

                role.hasPrivilege(privilege,result);

            }

            int max = -22;

            int falseCount = 0;

            for (Object object : result) {

                if(object.toString().equals("true")) {

                    return true;

                } else if(object.toString().equals("false")) {

                    falseCount++;

                } else {//是整数
                    int a = Integer.parseInt(object.toString());
                    max = a > max? a : max;
                }
            }
            //如果所有角色中都没有找到权限
            if(falseCount == result.size()) {
                return false;
            }
            //如果返回的是整数
            if(max!=-22) {
                return max;
            }
            return false;
        }
    }
}