package com.example.musicplay.service;

import com.aidl.IBook;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**  
 * 查询书籍的服务  
 * @author gbq  
 *  
 */  
public class BookService extends Service {  
      
    private String[] bookNames = {"Java编程思想","设计模式","Android开发设计"};   
      
    private IBinder mIBinder = new BookBinder();  
  
    @Override  
    public IBinder onBind(Intent intent) {  
        // TODO Auto-generated method stub  
        return mIBinder;  
    }  
      
    /**  
     * 服务中交互的方法  
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