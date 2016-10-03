import java.nio.ByteBuffer;
public class BTree{
	private BTFile file;
	private static int t = 0;
	private int seqLength = 0;
	private int sizeOfCache = 0;
	private boolean usingCache = false;
    private Cache<BTreeNode> cache;
	private BTreeNode root;

	public BTree(String fileName, int t, int length, int cacheSize){
		BTree.t = t;
		String fName = fileName;
		seqLength = length;
		sizeOfCache = cacheSize;
		if(sizeOfCache >0){
			usingCache = true;
			this.cache  = new Cache<BTreeNode>(sizeOfCache);
		}
		file = new BTFile(tFile, BTreeNode.BLOCKSIZE, t);
		BTreeNode x = new BTreeNode();
		x.leaf 1;
		x.numOfKeys = 0;
		if(usingCache){
			cacheUse(x);
		}else{
			writeToDisk(x);
		}
		x.setRoot(true);
    file.meta.setRoot(x.offset);
		root = x;
	}	
	
	/**
 	*	BTree constructor from existing file path and file containing BTree 
 	*	@param file 
 	*/
	public(String file){
		this.file = new BTFile(file);
		t = this.file.meta.getBTreeDegree();
		root = new BTreeNode(this.file.readData(this.file.meta.getBTRoot()));
		buildTree(root);
	}

	public void closeFile(){
		while(!cache.isEmpty()){
			writeToDisk(cache.removeFront());
		}
		file.fileClose();
	}

	/**
 	* BTree insert method takes in root and key. Starting at
 	* root and moving to and moving down to the tree looking
 	* for the proper leaf to put the key. Split if the all full nodes along the way	
 	*/ 
	public void insert(long temp){
		TreeObject k = new TreeObject(temp);
		BTreeNode r = root; //starting point

		//search tree for item before insertion
		TreeObject look = search(r, temp);
		if(temp == null){
			if(r.numOfKeys = 2*t-1){
				BTreeNode s = new BTreeNode();
				s.setRoot(true);
				r.setRoot(false);
		
			}
		}
	}
	
	public void cacheUse(BTreeNode x){
		if(cache.removeObject(x)){
			cache.addObject(x);
		} else {
			BTreeNode dump = cache.addObject(x);
			if(dump != null){
				writeToDisk(dump);
			}
		}
	}

	public BTreeNode getRoot(){
		return root;
	}
}
