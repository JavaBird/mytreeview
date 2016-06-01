package com.xuzelei.tree;

import java.io.Serializable;

/**
 * ���ӵ�
 * @author ������ 
 * xuzl@stelect.softserv.com.cn 
 * 252386922@qq.com 
 * Mobile Phone 13798397673
 *
 */

public class TreeNode implements Serializable {
	private static final long serialVersionUID = 4226755799531293257L;  
	private String id  ;//ID
	private String name ;//��ʾ�ı�
	private boolean isextends ;//չ��״̬	
	private String parent ;//����ID
	private boolean IsChecked;//��ѡ״̬
	private int Icon;
	
	public TreeNode() {		
	}
	public TreeNode(String id,String name,String parent,boolean isextends) {
		this.id=id;
		this.name=name;
		this.parent=parent;
		this.isextends=isextends;
	}
	public TreeNode(String id,String name,String parent,boolean isextends,int Icon) {
		this.id=id;
		this.name=name;
		this.parent=parent;
		this.isextends=isextends;
		this.Icon = Icon;
	}	
	public TreeNode(String id,String name,String parent,boolean isextends,boolean IsChecked) {
		this.id=id;
		this.name=name;
		this.parent=parent;
		this.isextends=isextends;
		this.IsChecked=IsChecked;
	}
	public TreeNode(String id,String name,String parent,boolean isextends,boolean IsChecked,int Icon) {
		this.id=id;
		this.name=name;
		this.parent=parent;
		this.isextends=isextends;
		this.IsChecked=IsChecked;
		this.Icon = Icon;
	}
	
	public boolean Getisextends() {
		return isextends;
	}
	
	public void Setisextends(boolean isextends) {
		this.isextends = isextends;
	}	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentNodeId() {
		return parent;
	}
	public void setParentNodeId(String parent) {
		this.parent = parent;
	}	
	public boolean GetIsChecked() {
		return IsChecked;
	}
	public void SetIsChecked(boolean IsChecked) {
		this.IsChecked = IsChecked;
	}	
	public int getIcon() {
		return Icon;
	}
	public void SetIcon(int Icon) {
		this.Icon = Icon;
	}	
}
