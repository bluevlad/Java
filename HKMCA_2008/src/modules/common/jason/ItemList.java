/*
 * $Id: ItemList.java,v 1.1 2012/10/31 00:19:57 cvsuser Exp $
 * Created on 2006-3-24
 */
package modules.common.jason;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * |a:b:c| => |a|,|b|,|c|
 * |:| => ||,||
 * |a:| => |a|,||
 * @author FangYidong<fangyidong@yahoo.com.cn>
 */
public class ItemList {
	private String sp=",";
	List items=new ArrayList();
	
	
	public ItemList(){}
	
	/**
	 * @param s
	 */
	public ItemList(String s){
		this.split(s,sp,items);
	}
	/**
	 * @param s
	 * @param sp
	 */
	public ItemList(String s,String sp){
		this.sp=s;
		this.split(s,sp,items);
	}
	
	/**
	 * @param s
	 * @param sp
	 * @param isMultiToken
	 */
	public ItemList(String s,String sp,boolean isMultiToken){
		split(s,sp,items,isMultiToken);
	}
	
	public List getItems(){
		return this.items;
	}
	
	public String[] getArray(){
		return (String[])this.items.toArray();
	}
	
	public void split(String s,String sp,List append,boolean isMultiToken){
		if(s==null || sp==null)
			return;
		if(isMultiToken){
			StringTokenizer tokens=new StringTokenizer(s,sp);
			while(tokens.hasMoreTokens()){
				append.add(tokens.nextToken().trim());
			}
		}
		else{
			this.split(s,sp,append);
		}
	}
	
	public void split(String s,String sp,List append){
		if(s==null || sp==null)
			return;
		int pos=0;
		int prevPos=0;
		do{
			prevPos=pos;
			pos=s.indexOf(sp,pos);
			if(pos==-1)
				break;
			append.add(s.substring(prevPos,pos).trim());
			pos+=sp.length();
		}while(pos!=-1);
		append.add(s.substring(prevPos).trim());
	}
	
	/**
	 * @param sp
	 */
	public void setSP(String sp){
		this.sp=sp;
	}
	
	/**
	 * @param i
	 * @param item
	 */
	public void add(int i,String item){
		if(item==null)
			return;
		items.add(i,item.trim());
	}
	/**
	 * @param item
	 */
	public void add(String item){
		if(item==null)
			return;
		items.add(item.trim());
	}
	
	/**
	 * @param list
	 */
	public void addAll(ItemList list){
		items.addAll(list.items);
	}
	
	/**
	 * @param s
	 */
	public void addAll(String s){
		this.split(s,sp,items);
	}
	
	/**
	 * @param s
	 * @param sp
	 */
	public void addAll(String s,String sp){
		this.split(s,sp,items);
	}
	
	public void addAll(String s,String sp,boolean isMultiToken){
		this.split(s,sp,items,isMultiToken);
	}
	
	/**
	 * @param i
	 * @return
	 */
	public String get(int i){
		return (String)items.get(i);
	}
	
	/**
	 * @return
	 */
	public int size(){
		return items.size();
	}
	/**
	 */
	public String toString(){
		return toString(sp);
	}
	
	/**
	 * @param sp
	 * @return
	 */
	public String toString(String sp){
		StringBuffer sb=new StringBuffer();
		
		for(int i=0;i<items.size();i++){
			if(i==0)
				sb.append(items.get(i));
			else{
				sb.append(sp);
				sb.append(items.get(i));
			}
		}
		return sb.toString();

	}
	
	/**
	 */
	public void clear(){
		items.clear();
	}
	
	/**
	 */
	public void reset(){
		sp=",";
		items.clear();
	}
}
