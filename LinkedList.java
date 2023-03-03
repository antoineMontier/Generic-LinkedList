public class LinkedList<U>{

  /**
   * pointeur to the header cell of the list
   */
  protected Cell<U> head;

  /**
   * pointeur to the last cell of the list
   */
  protected Cell<U> tail;

  /**
   * create a LinkedList without any cell (even head and tail)
   */
  public LinkedList() {
    head = null;
    tail = null;
  }

  /**
   * create a LinkedList by copying the param list, same content but not the same
   * adress
   */
  public LinkedList(LinkedList<U> l) {
    if (l.getHead() == null) {
      head = null;
      tail = null;
    } else if (l.getHead().getnext() == null) {
      head = new Cell<U>(l.getHead());
      tail = new Cell<U>(l.getHead());
    } else {
      head = new Cell<U>(l.getHead());
      Cell<U> walker1 = new Cell<U>(head);
      Cell<U> walker2 = new Cell<U>(head.getnext());
      // walker1.setnext(walker2);

      while (walker2.getnext() != null) {
        walker1 = walker2;
        walker2 = walker2.getnext();
        walker1.setnext(walker2);
      }

      walker1.setnext(walker2);
      tail = walker2;
    }
  }

  /**
   * @param nhead is the head we want to set as the head of the new Linked List
   */
  public LinkedList(Cell<U> nhead) {
    if (nhead == null) {// empty cell exception
      head = null;
      tail = null;
    } else if (nhead.getnext() == null) {// lonely cell exception
      head = nhead;
      tail = nhead;
    } else {
      Cell<U> walker = new Cell<U>(head);
      while (walker.getnext() != null) {
        walker = walker.getnext();
      }
      tail = walker;
    }
  }

  public LinkedList(U nObjhead) {
    Cell<U> h = new Cell<U>(nObjhead);
    head = h;
    tail = h;
  }

  /**
   * @return the last cell<U>
   */
  public Cell<U> getTail() {
    return tail;
  }

  /**
   * @param otail is the object we want to insert at the end of the linked List
   */
  public void addTail(U otail) {
    Cell<U> t = new Cell<U>(otail);
    if (isEmpty()) {
      tail = t;
      head = t;
      return;
    }
    tail.setnext(t);
    tail = t;
  }

  /**
   * @return the first cell<U>
   */
  public Cell<U> getHead() {
    return head;
  }

  /**
   * @param oHead is the object we want to insert at the end of the linked List
   */
  public void addHead(U oHead) {
    Cell<U> h = new Cell<U>(oHead);
    if (isEmpty()) {
      tail = h;
      head = h;
      return;
    }
    h.setnext(head);
    head = h;
  }

  /**
   * @return if the linkedlist is empty or not (if the header cell<U> exists or not)
   */
  public boolean isEmpty() {
    return head == null; // the list is empty if the header cell<U> hasn't been defined
  }

  /**
   * remove the head if the linked list isn't empty
   */
  public void removeHead() {
    if (isEmpty()) {
      return;// no changes needed if the linkedlist is already empty
    }

    Cell<U> temp = new Cell<U>();// keep in memory the header cell
    temp.setnext(head);
    head = head.getnext(); // head become the next cell
    temp = temp.getnext();// temp become the old head cell
    temp = null;// destroy the header cell
  }

  /**
   * destroy the tail, the cell before the tail become the new tail // working
   * perfectly
   */
  public void removeTail() {
    if (isEmpty()) {
      return;// no changes needed if the linkedlist is already empty
    }

    if (tail.equals(head)) {// if the tail and the head are the same cell, the list is one element
      tail = null;
      head = null;
      return;
    }

    Cell<U> temp = new Cell<U>();// this cell will be used to navigate into the linkedlist
    temp.setnext(head);
    while (!temp.getnext().equals(tail)) {// while the temp cell's next isn't tail, temp become the next cell and so on
      temp = temp.getnext();
    } // at this point temp is the cell just before tail
    tail = null;// delete tail
    tail = temp;// set the new tail
  }

  public String toString() {
    String r = "["; // string that will be returned

    if (isEmpty()) {
      r += "empty]";
      return r;
    }

    if (tail.equals(head)) {// if the tail and the head are the same cell, the list is one element
      return r + head + "]";
    }

    if (head.getnext().equals(tail)) {// third exception before the loop
      return r + head + ", " + tail + "]";
    }

    Cell<U> temp = new Cell<U>();// this cell will be used to navigate into the linkedlist
    temp.setnext(head);
    temp = temp.getnext(); // temps is now the header cell

    while (!temp.getnext().equals(tail)) {//// while the temp cell's next isn't tail
      r += temp + ", ";
      temp = temp.getnext();
    }
    r += temp + ", " + tail + "]";
    return r;
  }

  /**
   * @return the size of the linkedlist (starting at 0)
   */
  public int size() {
    if (isEmpty()) {
      return 0;
    }
    int count = 1;
    Cell<U> temp = new Cell<U>();
    temp.setnext(head);
    while (!temp.getnext().equals(tail)) {
      temp = temp.getnext();
      count++;
    }
    return count;
  }

  /**
   * @param pos position to insert : 0 means before head ; size() means after tail
   *            and n means between n-1 and n
   * @param o   object to insert
   */
  public void insertHere(int pos, U o) {
    // 3 exceptions
    if (pos > size() || pos < 0) {
      throw new IllegalArgumentException(
          "bad index \"" + pos + "\" for the list " + this + " in the insertHere function");
    }
    if (pos == 0) {
      addHead(o);
      return;
    }
    if (pos == size()) {
      addTail(o);
      return;
    }

    // general case

    Cell<U> temp = new Cell<U>();
    temp.setnext(head);
    // temp = temp.getnext(); //temp is located in the head

    for (int i = 0; i < pos; i++) {
      temp = temp.getnext();
    }
    // here, temp is the cell just before the "pos" cell

    Cell<U> newbie = new Cell<U>(o);
    newbie.setnext(temp.getnext());
    temp.setnext(newbie);
    return;
  }

  /**
   * @param pos is the position of the cell we need to remove ; 0 means you remove
   *            the head, size()-1 means you remove the tail and n means you
   *            remove the n cell // working perfectly
   */
  public void removeCell(int pos) {
    if (pos >= size() || pos < 0) {
      throw new IllegalArgumentException("bad index \"" + pos + "\" for the list " + this + " in the remove function");
    }
    if (pos == 0) {
      removeHead();
      return;
    }
    if (pos == size() - 1) {
      removeTail();
      return;
    }

    Cell<U> temp = new Cell<U>();
    temp.setnext(head);

    for (int i = 0; i < pos; i++) {
      temp = temp.getnext();
    }
    // here, temp is the cell just before the "pos" cell

    Cell<U> temp2 = new Cell<U>();
    temp2.setnext(temp.getnext());// temp and temp2 are on the same cell here
    temp2 = temp2.getnext();// temp2 is on the cell we need to destroy
    temp.setnext(temp2.getnext());
    temp2 = null;
  }

  /**
   * @param n is the index of the first value we want to swap
   * @param m is the index of the second value we want to swap
   */
  public void swap(int n, int m) {

    // two exceptions to catch (if the index ar out of the bounds of the arraylist)

    if (n >= size() || n < 0) {
      throw new IllegalArgumentException("bad index \"" + n + "\" for the list " + this + " in the remove function");
    }
    if (m >= size() || m < 0) {
      throw new IllegalArgumentException("bad index \"" + m + "\" for the list " + this + " in the remove function");
    }

    // exception if the user wants to swap the same cell : do nothing

    if (n == m) {
      return;
    }

    // here, n and m are differents and are both linked to an element inside the
    // linkedlist

    Cell<U> temp1 = new Cell<U>();
    temp1.setnext(head);

    Cell<U> temp2 = new Cell<U>();
    temp2.setnext(head);

    for (int i = 0; i <= n; i++) {
      temp1 = temp1.getnext();
    }

    for (int i = 0; i <= m; i++) {
      temp2 = temp2.getnext();
    }

    // here, temp1 is on the n cell and temp2 is on the m cell

    U buffer = temp1.getcontent();
    temp1.setcontent(temp2.getcontent());
    temp2.setcontent(buffer);

    buffer = null;
  }

  /**
   * reverse the order of the list, for example : [a, b, c] => [c, b, a]
   */
  public void reverse() {
    if (isEmpty() || size() == 1) {// no modifications to do if the list has 0 or 1 element
      return;
    }
    for (int i = 0; i < size() / 2; i++) {
      swap(i, size() - i - 1);
    }
  }

  /**
   * @return the content of the cell number n
   */
  public U get(int n) {
    if (n >= size() || n < 0) {
      throw new IllegalArgumentException("bad index \"" + n + "\" for the list " + this + " in the remove function");
    }
    Cell<U> buffer = new Cell<U>();
    buffer = head;
    for (int i = 0; i < n; i++) {
      buffer = buffer.getnext();
    }
    return buffer.getcontent();
  }

  /**
   * this method creates a new list which is the result of the merge of this and l
   * (in this precise order)
   * 
   * @param l is the list we want to merge
   */
  public LinkedList<U> merge(LinkedList<U> l) {

    if (isEmpty()) {// if this is empty, no changes to do on l with the same head as this
      return l;
    }
    if (l.isEmpty()) {// if l is empty, no chnages to do on this
      return this;
    }

    LinkedList<U> res = new LinkedList<U>();// create a new linkedlist (for the result)

    Cell<U> temp = new Cell<U>();
    temp.setnext(head);
    temp = temp.getnext();

    while (!temp.equals(tail)) {
      res.addTail(temp.content);
      temp = temp.getnext();
    }
    res.addTail(tail.content); // here, tail contains the linkedlist 'this' (from head to tail include)

    temp.setnext(l.getHead());
    temp = temp.getnext();// temp is the head of the second List

    while (!temp.equals(l.getTail())) {
      res.addTail(temp.content);
      temp = temp.getnext();
    }

    res.addTail(l.getTail().content);
    return res;
  }
}
