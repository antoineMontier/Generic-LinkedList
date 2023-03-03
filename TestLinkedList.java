public class TestLinkedList {
        public static void main(String[] args){
                LinkedList<String> list = new LinkedList<String>();
                list.addHead("aaa");
                list.addHead("ab");
                list.addTail("ee");
                list.insertHere(3, "AA");
                list.removeCell(0);
                System.out.println(list);
        }
}
