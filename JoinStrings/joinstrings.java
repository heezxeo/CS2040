//Ha HeeJu A0266301N

import java.io.*;
import java.util.*;

public class joinstrings {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());

        LinkedList[] stringArr = new LinkedList[n + 1];

        for (int i = 0; i < n; i++) {
            LinkedList nextList = new LinkedList();
            nextList.addNode(new Node(br.readLine()));
            stringArr[i] = nextList;
        }

        int result = 0; //dummy variable
        for (int j = 0; j < n  - 1; j++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            stringArr[a].addString(stringArr[b]);
            result = a;
        }

        Node cur = stringArr[result].head;
        out.print(cur.str);

        for (int i = 1; i < stringArr[result].size; i++){
            cur = cur.next;
            out.print(cur.str);
        }

        out.println();
        out.close();
    }
}

class Node {
    String str;
    Node next;

    Node(String str) {
        this.str = str;
        this.next = null;
    }

    void joinNext(Node next) {
        if (this.next != null){
            this.next.joinNext(next);
        } else {
            this.next = next;
        }
    }
}

class LinkedList {
    Node head;
    Node tail;
    int size;

    LinkedList() {
        size = 0;
    }

    void addNode(Node node) {
        node.joinNext(this.head);
        this.head = node;
        this.tail = head;
        size++;
    }

    void addString(LinkedList other) {
        tail.next = other.head;
        other.head = null;
        tail = other.tail;
        size += other.size;
    }
}
