package com.xuzelei.tree;

import java.util.ArrayList;
import java.util.List;

import android.util.AttributeSet;
//import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;

/**
 * �Զ����������
 * @author 252386922@qq.com
 *
 */
public class TreeView extends LinearLayout {
	/** ���ĳ�ʼ�ڵ㼯��. */
	public List<TreeNode> Nodes;
	
	/** �Ƿ���ʾ��ѡ��Ľڵ�����. */
	public boolean ShowCheckBoxes=false;
	
	/** Ĭ�Ͻڵ��ͼƬ. */
	public int NoExpandImage=0;
	
	/** չ���ڵ��ͼƬ. */
	public int ExpandImage=0;
	
	/** ��ѡ��ѡ��ͼƬ. */
	public int CheckOnImage=0;
	
	/** ��ѡ��δ��ѡ��ͼƬ. */
	public int CheckOffImage=0;
	
	/** �ڵ��������ɫ. */
	public int NodeColor=0;
	
	/** �����С*/
	public float TextSize = 0;
	
	/** �Ƿ���ʾ�ڵ�ͼ��*/
	public boolean isShowIcon;	
	/** ���õ����ڵ㡢��ʶ�͹�ѡ��֮��ľ��룬Ĭ��Ϊ2*/
	public int Margin=2;
	/** �Ƿ���ʾ���汳����ɫ*/
	public boolean ShowItemBackGroundColor;
	/** ���汳����ɫ��ֵ����ShowItemColor=trueʱ����Ч*/
	public int[] ItemBackgroundColor;	
	/** ��չ���ڵ�ʱ��չ�����е�������*/
	public int MarginLeftOnRowExpand=70;
	/** �ݹ�չ��ʱ���½ڵ�Ӧ���������λ�ã���ֵΪ�����ڵ�����ֵ+1+���ۼ����ӵ�����*/
	private int AddIndex = 0;
	/** �ݹ鹴ѡʱ���½ڵ������λ�ã���ֵΪ��ѡ�ڵ�����ֵ+1+���ۼƹ�ѡ������*/
	private int checkedIndex = 0;	
	
	private Context OfActivity;
	private OnNodeClickListener NodeClickListener;
	private OnNodeCheckClickListener NodeCheckClickListener;
	
	public TreeView(Context context) {
		super(context);
		OfActivity=context;
        this.setOrientation( LinearLayout.VERTICAL ); //��ֱ�����ӿؼ�        
	}
	public TreeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		OfActivity=context;
        this.setOrientation( LinearLayout.VERTICAL ); //��ֱ�����ӿؼ�
	}
	/**
	 * ��ȡ��ѡ�ڵ㼯��
	 * @return
	 */
	public List<TreeNode> getCheckNodes() {
		List<TreeNode> CheckNodes = new ArrayList<TreeNode>();
		if(null !=Nodes&&Nodes.size()>0) {
			for(TreeNode item:Nodes)
	    	{ 
	    		if(item.GetIsChecked()) {
	    			CheckNodes.add(item);//CheckNodes.addElement(item);
	    		}
	    	}  
		}		
		return CheckNodes;
	}
	/**
	 * �ڵ㹴ѡ�����¼�
	 * @param NodeCheckClickListener
	 */
	public void setOnNodeCheckClickListener(OnNodeCheckClickListener NodeCheckClickListener) {
		this.NodeCheckClickListener=NodeCheckClickListener;
	}
	/**
	 * �ڵ㵥���¼�
	 * @param NodeClickListener
	 */
	public void setOnNodeClickListener(OnNodeClickListener NodeClickListener) {
		this.NodeClickListener=NodeClickListener;
	}
	/**
	 * ��Ⱦ��
	 */
	public void Render(String RootNodeParentID)
	{
		List<TreeNode> tmpVector = GetChildsTreeNode(RootNodeParentID);
        createTree(OfActivity,tmpVector,this,1);//�ݹ齨����          
	}
	/**
	 * �ݹ�ʵ��������Ⱦ
	 * @param context �����Ķ���
	 * @param tmpVector ��Ҫ��Ⱦ�Ľڵ㼯��
	 * @param layout ��Ҫ�������layout
	 * @param lay ����
	 */
	private void createTree(Context context,List<TreeNode> tmpVector,LinearLayout layout,int lay)
    { 		
		layout.setGravity(Gravity.CENTER_VERTICAL);
    	if(tmpVector.size()>0)
    	{     		
    		for(TreeNode item:tmpVector)
    		{
    			//-------------�������--------------    			     			
    			List<TreeNode> itemChildNodes = GetChildsTreeNode(item.getId());    			
    			//--------------����Ϣ------------------
    			LinearLayout layouttmp = new LinearLayout(context);
    			layouttmp.setTag(lay);//�������ڵĲ㼶��   tag=level
    			layouttmp.setGravity(Gravity.CENTER_VERTICAL);
    			layouttmp.setOrientation(HORIZONTAL);
    			layouttmp.setPadding(5, 20, 5, 20);
    			if(ShowItemBackGroundColor) {
    				if((layout.getChildCount()&1)==0) {
        				layouttmp.setBackgroundColor(ItemBackgroundColor[0]);
        			}
    				else {
    					layouttmp.setBackgroundColor(ItemBackgroundColor[1]);
    				}
    			}
    			
//    			Log.i("treeview", "�ڵ�  "+item.getName() +"����Layout��tag��"+String.valueOf(lay));
    			//--------------�����ӵ�-----------------
    			if(this.ExpandImage==0) {
	    			TextView V_navigate = new TextView(context);
	    			V_navigate.setTag(item.getId());    	        
	    			if(NodeColor!=0) V_navigate.setTextColor(NodeColor);
	    			V_navigate.setClickable(true);
	    			V_navigate.setFocusable(true);
	    			if(!item.Getisextends())
	    			{    			
	    				if(itemChildNodes.size()>0) {
	    					V_navigate.setText("+");
	    				}
	    				else {
	    					V_navigate.setText("-");
	    				}    				
	    			}
	    			else
	    			{		
	    				V_navigate.setText("-");
	    			}
	    			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    				params.leftMargin = lay*MarginLeftOnRowExpand;
    				V_navigate.setLayoutParams(params);
//	    	        V_navigate.setPadding(0, 12, 0, 0);
	    	        V_navigate.setOnClickListener(new View.OnClickListener()
	    			{
	    				public void onClick(View v)
	    	        	{
	    					TreeNode item = getByTag(v.getTag().toString());    					
	    					
	    					if(item.Getisextends())//�����ǰ�Ѿ�չ���¼����ݹ������¼������ı䵱ǰ��isextends
	    					{
	    						//�ݹ������¼�
								LinearLayout cur = (LinearLayout)v.getParent();
								LinearLayout z = (LinearLayout)cur.getParent();
								int Layoutindex = z.indexOfChild(cur);
	    						
								int childsize = dgss(v.getTag().toString(),Layoutindex,z);
								if(childsize>0) 
								{
									((TextView)v).setText("+");
									
									//�ı䵱ǰ��isextends
		    						item.Setisextends(false);
		    						int index = Nodes.indexOf(item);
		    						Nodes.set(index, item);//Nodes.setElementAt(item, index);
								}    						
	    					}
							else//�����ǰû��չ���¼����ݹ�չ���¼������ı䵱ǰ��isextends
							{
								//�ݹ�չ���¼�
								LinearLayout cur = (LinearLayout)v.getParent();
								LinearLayout parentLinearLayout = (LinearLayout)cur.getParent();
								int Layoutindex = parentLinearLayout.indexOfChild(cur);
								    			
								AddIndex = Layoutindex+1;
								int childsize = dgzk(OfActivity,v.getTag().toString(),parentLinearLayout,Integer.parseInt(cur.getTag().toString())+1);
								if(childsize>0) 
								{
									((TextView)v).setText("-");
									
									//�ı䵱ǰ��isextends
		    						item.Setisextends(true);
		    						int index = Nodes.indexOf(item);
		    						Nodes.set(index, item);//Nodes.setElementAt(item, index);
								}							
							}
	    	        	}
	    			});
	    	        layouttmp.addView(V_navigate);
    			}
    			else
    			{
    				ImageView V_navigate = new ImageView(context);
    				LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    				params.leftMargin = lay*MarginLeftOnRowExpand;
    				V_navigate.setLayoutParams(params);
//    				V_navigate.setPadding(0, 12, 0, 0);
    				V_navigate.setTag(item.getId());
    				
    				if(!item.Getisextends())
        			{    			
        				if(itemChildNodes.size()>0) 
        				{
        					V_navigate.setImageResource(this.ExpandImage);
        				}
        				else
        				{
        					V_navigate.setImageResource(this.NoExpandImage);
        				}    				
        			}
        			else
        			{		
        				V_navigate.setImageResource(this.NoExpandImage);
        			}
    				
    				V_navigate.setOnClickListener(new View.OnClickListener()
	    			{
	    				public void onClick(View v)
	    	        	{ 
	    					TreeNode item = getByTag(v.getTag().toString());    					
	    					
	    					if(item.Getisextends())//�����ǰ�Ѿ�չ���¼����ݹ������¼������ı䵱ǰ��isextends
	    					{
	    						//�ݹ�
								LinearLayout cur = (LinearLayout)v.getParent();
								LinearLayout parentLinearLayout = (LinearLayout)cur.getParent();
								int Layoutindex = parentLinearLayout.indexOfChild(cur);
	    						
								int childsize = dgss(v.getTag().toString(),Layoutindex,parentLinearLayout);
								if(childsize>0) 
								{
									((ImageView)v).setImageResource(ExpandImage);
									
									//����
		    						item.Setisextends(false);
		    						int index = Nodes.indexOf(item);
		    						Nodes.set(index, item);//Nodes.setElementAt(item, index);
								}    						
	    					}
							else//�����ǰû��չ���¼����ݹ�չ���¼������ı䵱ǰ��isextends
							{
								//�ݹ�
								LinearLayout cur = (LinearLayout)v.getParent();
								LinearLayout parentLinearLayout = (LinearLayout)cur.getParent();
								int Layoutindex = parentLinearLayout.indexOfChild(cur);
								    			
								AddIndex = Layoutindex+1;
								int childsize = dgzk(OfActivity,v.getTag().toString(),parentLinearLayout,Integer.parseInt(cur.getTag().toString())+1);
								if(childsize>0) 
								{
									((ImageView)v).setImageResource(NoExpandImage);
									
									//չ��
		    						item.Setisextends(true);
		    						int index = Nodes.indexOf(item);
		    						Nodes.set(index, item);//Nodes.setElementAt(item, index);
								}							
							}
	    	        	}
	    			});
	    	        layouttmp.addView(V_navigate);
    			}
    			//---------------��ʶ�ڵ�ͼƬ-----------------
    			if(isShowIcon) {
    				ImageView typeImg = new ImageView(context); 
    				typeImg.setImageResource(item.getIcon());
            		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    				params.leftMargin = Margin;
    				typeImg.setLayoutParams(params);
            		layouttmp.addView(typeImg);
    			}   
    			//--------------checkͼƬ----------------
    			if(this.ShowCheckBoxes) {    				
	    			ImageView btn = new ImageView(context); 
	    			btn.setTag(item.getId());
//	    			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//    				params.leftMargin = Margin;
//    				btn.setLayoutParams(params);
	    			btn.setPadding(Margin, 0, 0, 0);
	    			if(item.GetIsChecked())
	    			{      
	    				btn.setImageResource(this.CheckOnImage);
	    			}
	    			else
	    			{		
	    				btn.setImageResource(this.CheckOffImage); 
	    			}    	
	    			
	    			btn.setOnClickListener(new View.OnClickListener()
	    			{
	    				public void onClick(View v)
	    	        	{  
	    					TreeNode item = getByTag(v.getTag().toString()); 
	    					if(item.GetIsChecked())
	    					{
	    						item.SetIsChecked(false);
	    						((ImageView)v).setImageResource(CheckOffImage);
	    					}
	    					else
	    					{
	    						item.SetIsChecked(true);
	    						((ImageView)v).setImageResource(CheckOnImage);
	    					}
	    					int index = Nodes.indexOf(item);
	    					Nodes.set(index, item);//Nodes.setElementAt(item, index);
	    					
	    					//�ݹ�ѡ���¼��ڵ�
	    					LinearLayout cur = (LinearLayout)v.getParent();
							LinearLayout z = (LinearLayout)cur.getParent();
							int Layoutindex = z.indexOfChild(cur);
							checkedIndex = Layoutindex+1;
							dgchecked(v.getTag().toString(),z,item.GetIsChecked(),item.Getisextends());
							dgcheckedchangeitem(v.getTag().toString(),item.GetIsChecked());
							
	    					if(NodeCheckClickListener!=null) {
	    						NodeCheckClickListener.OnNodeCheckClick(item);     	        			
        	        		}   
	    					
	    	        	}
	    			});
	    			
	    			layouttmp.addView(btn, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    			}
    			//--------------��ʾ�ı�---------------
    	        TextView tv = new TextView(context);   
    	        tv.setTag(item.getId());
    	        tv.setText( item.getName() ); 
    	        tv.setGravity(Gravity.CENTER_VERTICAL);
    	        if(TextSize!=0) tv.setTextSize(TextSize);
    	        if(NodeColor!=0) tv.setTextColor(NodeColor);
    	        
    	        tv.setPadding(5, 0, 0, 0);
    	        tv.setClickable(true);
    	        tv.setFocusable(true);
    	        //�ڵ㵥���¼�
    	        tv.setOnClickListener(new View.OnClickListener()
    	        {
    	        	public void onClick(View v)
    	        	{ 
    	        		if(NodeClickListener!=null) {    	        			
    	        			TreeNode item = getByTag(v.getTag().toString()); 
    	        			NodeClickListener.onNodeClick(item);     	        			
    	        		}   	        		     	        		
    	        	}
	        	});
    	        /*
    	        tv.setOnFocusChangeListener(new View.OnFocusChangeListener()
    	        {
    	        	public void onFocusChange(View v,boolean isfocus)
    	        	{
    	        		TextView tv = (TextView)v;
    	        		if(isfocus)
    	        		{
    	        			tv.setTextColor(Color.rgb(0, 0, 255));
    	        		}
    	        		else
    	        		{	
    	        			tv.setTextColor(Color.rgb(255, 255, 255));
    	        		}    	        		
    	        	}
    	        }); */
    	        layouttmp.addView(tv);
    	        //-----------------
    	        layout.addView(layouttmp);
    	        if(item.Getisextends())
    	        {
    	        	createTree(context,itemChildNodes,layout,lay+1);
    	        }
    		}		
    	}    	   
    }
	/**
	 * ���ͼƬʱ���ݹ�չ�����ӽڵ�
	 * @param context �����Ķ���������̬�����ؼ�
	 * @param itemid ��ǰ�ڵ��IDֵ
	 * @param z �������ⲿ��layout
	 * @param lay ��չ���Ľڵ�Ĳ�������������left padding
	 * @return һ���ӽڵ�ĸ���
	 */
    private int dgzk(Context context,String itemid,LinearLayout z,int lay)//�ݹ�չ��
    {
    	List<TreeNode> aa = GetChildsTreeNode(itemid);
    	if(aa!=null && aa.size()>0) {
    		for(TreeNode item : aa)
    		{    		
        		//-------------�������--------------    		
        		List<TreeNode> itemChildNodes = GetChildsTreeNode(item.getId());
        		//--------------����Ϣ------------------
        		LinearLayout layouttmp = new LinearLayout(context);
        		layouttmp.setTag(lay); 
        		layouttmp.setGravity(Gravity.CENTER_VERTICAL);
        		layouttmp.setOrientation(HORIZONTAL);
        		layouttmp.setPadding(5, 20, 5, 20);
        		if(ShowItemBackGroundColor) {
    				if((z.getChildCount()&1)==0) {
        				layouttmp.setBackgroundColor(ItemBackgroundColor[0]);
        			}
    				else {
    					layouttmp.setBackgroundColor(ItemBackgroundColor[1]);
    				}
    			}
        		//--------------�����ӵ�-----------------
        		if(this.ExpandImage==0) {
        			TextView V_navigate = new TextView(context);
        			V_navigate.setTag(item.getId());    	        
        			if(NodeColor!=0) V_navigate.setTextColor(NodeColor);
        			V_navigate.setClickable(true);
        			V_navigate.setFocusable(true);
        	        if(!item.Getisextends())
        			{    			
        				if(itemChildNodes.size()>0) {
        					V_navigate.setText("+");
        				}
        				else {
        					V_navigate.setText("-");
        				}    				
        			}
        			else
        			{		
        				V_navigate.setText("-");
        			}
        	        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        	        params.leftMargin = lay*MarginLeftOnRowExpand;
        	        V_navigate.setLayoutParams(params);
//        	        V_navigate.setPadding(0, 12, 0, 0);
        	        V_navigate.setOnClickListener(new View.OnClickListener()
        			{
        				public void onClick(View v)
        	        	{ 
        					TreeNode item = getByTag(v.getTag().toString());    					
        					
        					if(item.Getisextends())//�����ǰ�Ѿ�չ���¼����ݹ������¼������ı䵱ǰ��isextends
        					{
        						//�ݹ�
    							LinearLayout cur = (LinearLayout)v.getParent();
    							LinearLayout z = (LinearLayout)cur.getParent();
    							int Layoutindex = z.indexOfChild(cur);
        						
    							int childsize = dgss(v.getTag().toString(),Layoutindex,z);
    							if(childsize>0) 
    							{
    								((TextView)v).setText("+");
    								
    								//����
    	    						item.Setisextends(false);
    	    						int index = Nodes.indexOf(item);
    	    						Nodes.set(index, item);//Nodes.setElementAt(item, index);
    							}    						
        					}
    						else//�����ǰû��չ���¼����ݹ�չ���¼������ı䵱ǰ��isextends
    						{
    							//�ݹ�
    							LinearLayout cur = (LinearLayout)v.getParent();
    							LinearLayout z = (LinearLayout)cur.getParent();
    							int Layoutindex = z.indexOfChild(cur);
    							    			
    							AddIndex = Layoutindex+1;
    							int childsize = dgzk(OfActivity,v.getTag().toString(),z,Integer.parseInt(cur.getTag().toString())+1);
    							if(childsize>0) 
    							{
    								((TextView)v).setText("-");
    								
    								//չ��
    	    						item.Setisextends(true);
    	    						int index = Nodes.indexOf(item);
    	    						Nodes.set(index, item);//Nodes.setElementAt(item, index);
    							}							
    						}
        	        	}
        			});
        	        layouttmp.addView(V_navigate);
    			}
    			else
    			{
    				ImageView V_navigate = new ImageView(context); 
    				LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        	        params.leftMargin = lay*MarginLeftOnRowExpand;
        	        V_navigate.setLayoutParams(params);
//    				V_navigate.setPadding(0, 12, 0, 0);
    				V_navigate.setTag(item.getId());
    				
    				if(!item.Getisextends())
        			{    			
        				if(itemChildNodes.size()>0) 
        				{
        					V_navigate.setImageResource(this.ExpandImage);
        				}
        				else
        				{
        					V_navigate.setImageResource(this.NoExpandImage);
        				}    				
        			}
        			else
        			{		
        				V_navigate.setImageResource(this.NoExpandImage);
        			}
    				
    				V_navigate.setOnClickListener(new View.OnClickListener()
        			{
        				public void onClick(View v)
        	        	{  
        					TreeNode item = getByTag(v.getTag().toString());    					
        					
        					if(item.Getisextends())//�����ǰ�Ѿ�չ���¼����ݹ������¼������ı䵱ǰ��isextends
        					{
        						//�ݹ�
    							LinearLayout cur = (LinearLayout)v.getParent();
    							LinearLayout z = (LinearLayout)cur.getParent();
    							int Layoutindex = z.indexOfChild(cur);
        						
    							int childsize = dgss(v.getTag().toString(),Layoutindex,z);
    							if(childsize>0) 
    							{
    								((ImageView)v).setImageResource(ExpandImage);
    								
    								//����
    	    						item.Setisextends(false);
    	    						int index = Nodes.indexOf(item);
    	    						Nodes.set(index, item);//Nodes.setElementAt(item, index);
    							}    						
        					}
    						else//�����ǰû��չ���¼����ݹ�չ���¼������ı䵱ǰ��isextends
    						{
    							//�ݹ�
    							LinearLayout cur = (LinearLayout)v.getParent();
    							LinearLayout z = (LinearLayout)cur.getParent();
    							int Layoutindex = z.indexOfChild(cur);
    							    			
    							AddIndex = Layoutindex+1;
    							int childsize = dgzk(OfActivity,v.getTag().toString(),z,Integer.parseInt(cur.getTag().toString())+1);
    							if(childsize>0) 
    							{
    								((ImageView)v).setImageResource(NoExpandImage);
    								
    								//չ��
    	    						item.Setisextends(true);
    	    						int index = Nodes.indexOf(item);
    	    						Nodes.set(index, item);//Nodes.setElementAt(item, index);
    							}							
    						}
        	        	}
        			});
        	        layouttmp.addView(V_navigate);
    			}
        		//---------------��ʶ�ڵ�ͼƬ-----------------
    			if(isShowIcon) {
    				ImageView typeImg = new ImageView(context); 
    				typeImg.setImageResource(item.getIcon());
            		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    				params.leftMargin = Margin;
    				typeImg.setLayoutParams(params);
            		layouttmp.addView(typeImg);
    			}   
    	      //--------------checkͼƬ----------------
        		if(this.ShowCheckBoxes) {    
    				ImageView btn = new ImageView(context); 
    				btn.setTag(item.getId());
//    				btn.setPadding(2, 12, 0, 0);
    				btn.setPadding(Margin, 0, 0, 0);
//    				LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//    				params.leftMargin = Margin;
//    				btn.setLayoutParams(params);
    				
    				if(item.GetIsChecked())
    				{ 
    					btn.setImageResource(this.CheckOnImage); 
    				}
    				else
    				{		
    					btn.setImageResource(this.CheckOffImage); 
    				}    	
    				
    				btn.setOnClickListener(new View.OnClickListener()
    				{
    					public void onClick(View v)
    		        	{   
    						TreeNode item = getByTag(v.getTag().toString()); 
    						if(item.GetIsChecked())
    						{
    							item.SetIsChecked(false);
    							((ImageView)v).setImageResource(CheckOffImage);
    						}
    						else
    						{
    							item.SetIsChecked(true);
    							((ImageView)v).setImageResource(CheckOnImage);
    						}
    						int index = Nodes.indexOf(item);
    						Nodes.set(index, item);//Nodes.setElementAt(item, index);
    						
    						//�ݹ�ѡ���¼��ڵ�
        					LinearLayout cur = (LinearLayout)v.getParent();
    						LinearLayout z = (LinearLayout)cur.getParent();
    						int Layoutindex = z.indexOfChild(cur);
    						checkedIndex = Layoutindex+1;
    						dgchecked(v.getTag().toString(),z,item.GetIsChecked(),item.Getisextends());
    						
							dgcheckedchangeitem(v.getTag().toString(),item.GetIsChecked());
    						if(NodeCheckClickListener!=null) {
        						NodeCheckClickListener.OnNodeCheckClick(item);     	        			
        	        		} 
    		        	}
    				});
    				layouttmp.addView(btn, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        		}
    			//--------------��ʾ�ı�---------------
    			TextView tv = new TextView(context);   
    	        tv.setTag(item.getId());
    	        tv.setText( item.getName() );  
    	        tv.setGravity(Gravity.CENTER_VERTICAL);
    	        if(TextSize!=0) tv.setTextSize(TextSize);
    	        if(NodeColor!=0)  tv.setTextColor(this.NodeColor);
    	        tv.setPadding(5, 0, 0, 0);
    	        tv.setClickable(true);
    	        tv.setFocusable(true);
    	        //�ڵ㵥���¼�
    	        tv.setOnClickListener(new View.OnClickListener()
    	        {
    	        	public void onClick(View v)
    	        	{ 
    	        		if(NodeClickListener!=null) {
    	        			TreeNode item = getByTag(v.getTag().toString()); 
    	        			NodeClickListener.onNodeClick(item);     	        			
    	        		}       	        		     	        		
    	        	}
            	});        
    	        /*tv.setOnFocusChangeListener(new View.OnFocusChangeListener()
    	        {
    	        	public void onFocusChange(View v,boolean isfocus)
    	        	{
    	        		TextView tv = (TextView)v;
    	        		if(isfocus)
    	        		{
    	        			tv.setTextColor(Color.rgb(0, 0, 255));
    	        		}
    	        		else
    	        		{	
    	        			tv.setTextColor(Color.rgb(255, 255, 255));
    	        		}    	        		
    	        	}
    	        }); */
    	        layouttmp.addView(tv);
    	        //--------------------------------------	
    	        z.addView(layouttmp, AddIndex);
//    	        layouttmp.setBackgroundColor(Color.argb(200, 200, 8, 7));
    	        AddIndex ++;
        		
        		if(item.Getisextends())
        		{
        			dgzk(context,item.getId(),z,lay+1);
        		}
    		}
    	}
    	
    	return aa.size();
    }
    /**
     * �ݹ鹴ѡ�¼��ڵ�
     * @param itemid ��ǰ��ѡ��itemid
     * @param z ����layout
     * @param ischecked �Ƿ�ѡ״̬
     * @param isExtends �Ƿ�չ��״̬
     */
    private void dgchecked(String itemid,LinearLayout z,boolean ischecked,boolean isExtends)//�ݹ鹴ѡ
    {
    	//����¼��ڵ�û��չ����ֱ�ӷ���
    	if(!isExtends) return;
    	//�ݹ鹴ѡ�ڵ�
    	List<TreeNode> aa = GetChildsTreeNode(itemid);
    	if(aa!=null && aa.size()>0) {
    		for(TreeNode item : aa)
    		{			
    			LinearLayout curr = (LinearLayout)z.getChildAt(checkedIndex);
    			
    			if(ischecked)
    			{
    				if(isShowIcon) {
    					((ImageView)curr.getChildAt(2)).setImageResource(this.CheckOnImage);
        			}   
    				else {
    					((ImageView)curr.getChildAt(1)).setImageResource(this.CheckOnImage);
    				}
    			}
    			else
    			{
    				if(isShowIcon) {
    					((ImageView)curr.getChildAt(2)).setImageResource(this.CheckOffImage);
        			}   
    				else {
    					((ImageView)curr.getChildAt(1)).setImageResource(this.CheckOffImage);
    				}
    			}
    			
    			checkedIndex ++;
    			dgchecked(item.getId(),z,ischecked, item.Getisextends());
    		}
    	}
    }
    private void dgcheckedchangeitem(String itemid, boolean ischecked) {
    	List<TreeNode> aa = GetChildsTreeNode(itemid);
    	if(aa!=null && aa.size()>0) {
    		for(TreeNode item : aa)	{		
    			item.SetIsChecked(ischecked);
    			dgcheckedchangeitem(item.getId(),ischecked);
    		}
    	}
    }
    private int dgss(String itemid,int Layoutindex,LinearLayout z)//�ݹ�����
    {
    	List<TreeNode> aa = GetChildsTreeNode(itemid);
    	if(aa!=null && aa.size()>0) {
    		for(TreeNode item : aa) {
    			z.removeViewAt(Layoutindex+1);	
    			if(item.Getisextends())
    			{
    				dgss(item.getId(),Layoutindex,z);
    			}
    		}
//    		for(int i=0;i<aa.size();i++)
//    		{
//    			TreeNode item = aa.get(i);//(TreeNode) aa.elementAt(i);
////    			Log.i("treeview", "����  "+item.getName());
//    			z.removeViewAt(Layoutindex+1);	
//    			if(item.Getisextends())
//    			{
//    				dgss(item.getId(),Layoutindex,z);
//    			}
//    		}
    	}
		
		return aa.size();
    }
    private TreeNode getByTag(String tag)
    {
    	if(Nodes.size()>0) {
    		for(TreeNode node:Nodes) {
        		if(node.getId().equals(tag)) return node;
        	}
    	}
    	return null;
    }
    private List<TreeNode> GetChildsTreeNode(String Pid)
    {
    	List<TreeNode> tmpVector = new ArrayList<TreeNode>();
    	if(Nodes.size()>0) {
    		for(TreeNode item : Nodes)
        	{   
        		if(item.getParentNodeId().equals(Pid))
        		{
        			tmpVector.add(item);
        		}
        	}
    	}
    	return tmpVector;
    }	
}
