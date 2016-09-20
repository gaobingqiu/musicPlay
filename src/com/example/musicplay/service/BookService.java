package com.example.musicplay.service;

import com.aidl.IBook;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**  
 * ��ѯ�鼮�ķ���  
 * @author gbq  
 *  
 */  
public class BookService extends Service {  
      
    private String[] bookNames = {"Java���˼��","���ģʽ","Android�������"};   
      
    private IBinder mIBinder = new BookBinder();  
  
    @Override  
    public IBinder onBind(Intent intent) {  
        // TODO Auto-generated method stub  
        return mIBinder;  
    }  
      
    /**  
     * �����н����ķ���  
     * @param bookNo  
     * @return  
     */  
    public String queryBookName(int bookNo){  
        if(bookNo > 0 && bookNo <= bookNames.length){  
            return bookNames[bookNo-1];  
        }  
        return null;  
    }  
      
    private class BookBinder extends IBook.Stub{  
  
        @Override  
        public String queryBook(int bookNo) throws RemoteException {  
            return queryBookName(bookNo);  
        }  
          
    }  
  
}  