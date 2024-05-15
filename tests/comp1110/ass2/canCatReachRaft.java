//package comp1110.ass2;
//
//import javax.swing.text.Position;
//import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Set;
//
//public class canCatReachRaft {
//
//    Position position;
//    public boolean canCatReachRaft(TheBoard board) {
//        // 假设有一个表示猫位置的变量 catPosition 和木筏位置 raftPosition
//        Position catPosition = board.getCatPosition();
//        Position raftPosition = board.getRaftPosition();
//
//        // 使用 BFS 或 DFS 搜索路径
//        Queue<Position> queue = new LinkedList<>();
//        Set<Position> visited = new HashSet<>();
//
//        queue.add(catPosition);
//        visited.add(catPosition);
//
//        while (!queue.isEmpty()) {
//            Position current = queue.poll();
//            if (current.equals(raftPosition)) {
//                return true; // 找到路径
//            }
//
//            for (Position neighbor : board.getNeighbors(current)) {
//                if (!visited.contains(neighbor) && board.isPassable(neighbor)) {
//                    queue.add(neighbor);
//                    visited.add(neighbor);
//                }
//            }
//        }
//
//        return false; // 无法到达木筏
//    }
//
//}
