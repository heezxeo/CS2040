//Ha HeeJu A0266301N

import java.util.*;
import java.io.*;

public class nicknames {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        HashMap<String, Integer> matches = new HashMap<String, Integer>();
        HashMap<Character, AVLtree> namelist = new HashMap<Character, AVLtree>();

        int numnames = Integer.parseInt(sc.readLine());

        for (int i = 0; i < numnames; i++) {
            String name = sc.readLine();
            char first = name.charAt(0);

            if (namelist.containsKey(first)) {
                namelist.get(first).insert(name);
            } else {
                AVLtree avl = new AVLtree();
                avl.insert(name);
                namelist.put(first, avl);
            }
        }

        int numnickn = Integer.parseInt(sc.readLine());

        for (int j = 0; j < numnickn; j++) {
            String nickn = sc.readLine();
            char firstletter = nickn.charAt(0);
            int curr;

            if (matches.containsKey(nickn)) {
                curr = matches.get(nickn);
            } else {
                if (namelist.containsKey(firstletter)) {
                    curr = namelist.get(firstletter).check(nickn);
                } else {
                    curr = 0;
                }
                matches.put(nickn, curr);
            }
            pw.write(curr + "\n");
        }
        pw.close();
    }
}

class Vertex {
	Vertex right;
	Vertex left;
	Vertex parent;
	String key;
	int height;
	int size;

	Vertex (String key) {
		this.right = null;
		this.left = null;
		this.parent = null;
		this.key = key;
		this.height = 0;
		this.size = 1;
	}
}

class AVLtree {
	Vertex vertex;

	AVLtree() {
		this.vertex = null;
	}

	void inorder() {
		inorder(this.vertex);
	}

	void inorder(Vertex vertex) {
		if (vertex != null) {
			inorder(vertex.left);
			String currentV = vertex.key;
			System.out.println(currentV + " ");
			inorder(vertex.right);
		}
	}

	void insert(String str) {
    	this.vertex = insert(this.vertex, str);
	}

	Vertex insert(Vertex vertex, String str) {
    	if (vertex == null) {
        	return new Vertex(str);
    	}

    	String curr = vertex.key;

    	if (str.compareTo(curr) < 0) {
        	vertex.left = insert(vertex.left, str);
        	if (vertex.left != null) {
            	vertex.left.parent = vertex;
        	}
    	} else if (str.compareTo(curr) > 0) {
        	vertex.right = insert(vertex.right, str);
        	if (vertex.right != null) {
            	vertex.right.parent = vertex;
       	 	}
    	}

    	updateSize(vertex);
    	updateHeight(vertex);
    	return balance(vertex);
	}

	int height (Vertex vertex) {
		if (vertex != null) {
			return vertex.height;
		} else {
			return -1;
		}
	}

	void updateHeight (Vertex vertex) {
		if (vertex != null) {
			vertex.height = Math.max(height(vertex.left), height(vertex.right)) + 1;
		}
	}

	int size (Vertex vertex) {
		if (vertex != null) {
			return vertex.size;
		} else {
			return 0;
		}
	}

	void updateSize (Vertex vertex) {
		if (vertex != null) {
			vertex.size = size(vertex.left) + size(vertex.right) + 1;
		}
	}

	int bal (Vertex vertex) {
		if (vertex != null) {
			return height(vertex.left) - height(vertex.right);
		} else {
			return 0;
		}
	}

	Vertex balance (Vertex vertex) {
		if (bal(vertex) <= -2) {
			if (bal(vertex.right) >= 1) {
				vertex.right = rightrotate(vertex.right);
			}
			vertex = leftrotate(vertex);

		} else if (bal(vertex) >= 2) {
			if (bal(vertex.left) <= -1) {
				vertex.left = leftrotate(vertex.left);
			}
			vertex = rightrotate(vertex);
		}
		return vertex;
	}

	int check (String str) {
		Vertex valid = highestVertex (this.vertex, str);
		if (valid != null) {
			return 1 + traverseRight (valid.right, str) + traverseLeft (valid.left, str);
		}
		return 0;
	}

	Vertex highestVertex(Vertex vertex, String str) {
		if (vertex == null) {
			return null;
		}

		String curr = vertex.key;
		if (curr.indexOf(str) == 0) {
			return vertex;
		} else if (str.compareTo(curr) < 0) {
			return highestVertex (vertex.left, str);
		} else if (str.compareTo(curr) > 0) {
			return highestVertex(vertex.right, str);
		}

		return null;
	}

	Vertex leftrotate (Vertex vertex) {
		if (vertex.right != null) {
			Vertex rightV = vertex.right;
			vertex.right = rightV.left;
			if (rightV.left != null) {
				rightV.left.parent = vertex;
			}

			rightV.parent = vertex.parent;
			if (vertex.parent == null) {
				this.vertex = rightV;
			} else if (vertex == vertex.parent.left) {
				vertex.parent.left = rightV;
			} else {
				vertex.parent.right = rightV;
			}
			rightV.left = vertex;
			vertex.parent = rightV;

			rightV.size = vertex.size;
			updateSize(vertex);
			updateHeight(vertex);
			updateHeight(rightV);
			return rightV;
		}
		return vertex;
	}

	Vertex rightrotate (Vertex vertex) {
		if (vertex.left != null) {
			Vertex leftV = vertex.left;
			vertex.left = leftV.right;
			if (leftV.right != null) {
				leftV.right.parent = vertex;
			}

			leftV.parent = vertex.parent;
			if (vertex.parent == null) {
				this.vertex = leftV;
			} else if (vertex == vertex.parent.right) {
				vertex.parent.right = leftV;
			} else {
				vertex.parent.left = leftV;
			}

			leftV.right = vertex;
			vertex.parent = leftV;

			leftV.size = vertex.size;
			updateHeight(vertex);
			updateSize(vertex);
			updateHeight(leftV);
			return leftV;
		}
		return vertex;
	}

	int traverseRight (Vertex vertex, String str) {
		if (vertex == null) {
			return 0;
		}
		String curr = vertex.key;
		if (curr.indexOf(str) == 0) {
			return 1 + traverseRight(vertex.right, str) + size(vertex.left);
		} else {
			return traverseRight(vertex.left, str);
		}
	}

	int traverseLeft (Vertex vertex, String str) {
		if (vertex == null) {
			return 0;
		}
		String curr = vertex.key;
		if (curr.indexOf(str) == 0) {
			return 1 + traverseLeft(vertex.left, str) + size(vertex.right);
		} else {
			return traverseLeft(vertex.right, str);
		}
	}
}