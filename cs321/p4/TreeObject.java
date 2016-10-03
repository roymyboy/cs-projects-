import java.nio.ByteBuffer;

public class TreeObject{
	public static final int objSize = 12
	long data; 
	int freq;
	
	/**
 	* constructor
 	* @param data
 	*/ 
	public class TreeObject(long data){
		this.data = data;
		freq = 1;
	}
	
	/**
 	* using byte array to create Tree Object
 	* @param data byte array
 	*/ 
	public TreeObject(byte [] data){
		if(data.length != objSize){
			System.out.printf("Invalid byte array: Wrong byte size.\n");
			System.exit(0);
		}
		ByteBuffer buffer = ByteBuffer.allocate(objSize);
		buffer.put(data);
		buffer.rewind();
		this.data = buffer.getLong();
		this.freq = buffer.getInt();
	}

	/**
 	* byte array of this tree object. 
 	* creating byte buffer and inserting data
 	* @return byte[]
 	*/ 
	public byte[] getByteData(){
		ByteBuffer buffer = ByteBuffer.allocate(objSize);
		buffer.putLong(data);
		buffer.putInt(freq);
	
		return buffer.array();
	}

	/**
 	* compare method
 	* @param object
 	*/ 
	public int compareTo(TreeObject object){
		if(this.getData() < object.getData()) return -1;
		if(this.getData() > object.getData()) return 1;
		else return 0; 
	}
	
	public int getFrequency(){return freq;} //return frequency
	public void increamentFrequency(){freq++;} //increase the frequency 
	public long getData(){return data;} //return data 
	@Override
	public boolean equals(Object obj){return super.equals(obj);} //equate the obj to data
	@Override
	public String toString(){return super.toString();} //toString
}
