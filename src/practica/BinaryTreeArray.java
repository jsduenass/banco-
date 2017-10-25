/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica;

/**
 *
 * @author juan sebastian
 */import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BinaryTreeArray implements Comparable<BinaryTreeArray>{

	int data[];

	public BinaryTreeArray(){
		this.data = new int[ 1048575 ];
		Arrays.fill(data, -1);
	}

	public void add( int element ){

		int i = 1;
		while( true ){

			if( data[ i - 1 ] == -1 ){
				data[ i - 1 ] = element;
				break;
			}else if( data[ i -1 ] > element ){
				i = i * 2;
			}else{
				i = ( i * 2 ) + 1;
			}
		}

	}

	@Override
	public int compareTo(BinaryTreeArray arg0) {

		for( int i = 0; i < 1048575; i++ ){
			if( data[i] != -1 )
				if( arg0.data[i] == -1 )
					return -1;
			if( arg0.data[i] != -1 )
				if( data[i] == -1 )
					return -1;
		}

		return 0;
	}

	@Override
	public String toString(){
		StringBuilder s = new StringBuilder();
		for( int i = 0; i < 20; i++ )
			s.append( data[i] + " " );
		return new String( s );
	}

	public static void main(String[] args) throws IOException{
                File f= new File("practica.txt");
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		ArrayList<BinaryTreeArray> arr = new ArrayList<>();

		String s [] = br.readLine().split( "\\s" );

		int n = Integer.parseInt( s[0] );
		int k = Integer.parseInt( s[1] );

		for( int i = 0 ; i < n ; i++ ){
			s = br.readLine().split( "\\s" );
			BinaryTreeArray temp = new BinaryTreeArray();

			for( int j = 0 ; j < k ; j++ )
				temp.add( Integer.parseInt( s[j] ) );

			boolean add = true;

			if( arr.isEmpty() ){
				add = true;
			}else{
				for( BinaryTreeArray bta : arr )
					if( bta.compareTo(temp) == 0 ){
						add = false;
//						System.out.println( (i+1) + " IGUAl" );
						break;
					}
			}

			if( add ){
				arr.add( temp );
//				System.out.println( "Agrego " + (i+1) );
			}

		}

		System.out.println( arr.size() );


	}

}
