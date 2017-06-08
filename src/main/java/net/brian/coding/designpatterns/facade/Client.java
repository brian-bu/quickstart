package net.brian.coding.designpatterns.facade;

class DrawerOne {  
    public void open(){  
       System.out.println("Opening the first drawer...");  
       getKey();  
    }  
    public void getKey(){  
       System.out.println("Found the key of the second drawer...");  
    }  
}  
class DrawerTwo{  
    public void open(){  
       System.out.println("Opening the second drawer...");  
       getFile();  
    }  
    public void getFile(){  
       System.out.println("Found an important file...");  
    }  
}  
class DrawerFacade{  
    DrawerOne darwerOne=new DrawerOne();  
    DrawerTwo darwerTwo=new DrawerTwo();  
    public void open(){  
       darwerOne.open();  
       darwerTwo.open();  
    }  
}  
public class Client{  
    public static void main(String []args){  
       DrawerFacade drawer=new DrawerFacade();  
       drawer.open();  
    }  
}  