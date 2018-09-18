package org.slf4j;
//import Main;

public class LoggerFactory{//   .attrs.xml
    
    private LoggerFactory(){
        
    }

    public static Logger getLogger(Class reSugarCode){
        return new AndroidLogger(reSugarCode.getName());
    }
    
    private static class AndroidLogger implements Logger{

        @Override
        public boolean isDebugEnabled(){
            return true;
        }
        
        @Override
        public void info(String p0,Object[] p1){
           /* String str=p0;
			if(Main.dubug) {
			for(Object o:p1){
                if(o instanceof String){
                    str+=msg_+o+"\n";
                }
                else if(o instanceof CharSequence){
                    str+=msg_+o+"\n";
                }
                else{
                    str+=msg_+o.getClass().getName()+"\n";
                }
            }
          System.out.println(str);}*/
      // System.out.println("info"+msg_);
		 }

        @Override
        public void warn(String p0,Object[] p1){
          /*  String str=p0;
            if(Main.dubug) {
			for(Object o:p1){
                if(o instanceof String){
                    str+=msg_+o+"\n";
                }
                else if(o instanceof CharSequence){
                    str+=msg_+o+"\n";
                }
                else{
                    str+=msg_+o.getClass().getName()+"\n";
                } 
            }
           System.out.println(   str);}*/
      // System.out.println("warn"+msg_);
		   }

        @Override
        public void error(String p0,Object[] p1){
           /* String str=p0;
			if(Main.dubug) 
				{
			for(Object o:p1){
                if(o instanceof String||o instanceof Integer){
                    str+=msg_+o+"\n";
                }
                else if(o instanceof CharSequence){
                    str+=msg_+o+"\n";
                }
                else{
                    str+=msg_+o.getClass().getName()+"\n";
                }       
            }
          System.out.println(   str);}*/
     //  System.out.println("errer"+msg_);
		 }

        @Override
        public void debug(String p0,Object[] p1){
            /*String str=p0;
          if(Main.dubug) {
			for(Object o:p1){
                if(o instanceof String){
                    str+=msg_+o+"\n";
                }
                else if(o instanceof CharSequence){
                    str+=msg_+o+"\n";
                }
                else{
                    str+=msg_+o.getClass().getName()+"\n";
                }     
            }
          System.out.println(  str);}*/
     //  System.out.println("debug"+msg_);
		  }
     
        private AndroidLogger(String msg){
       /*  try{
			msg_=msg.substring(msg.lastIndexOf(".")+1)+": ";
      
			}catch(Exception e){
				msg_="name errer";
			}*/
			}
		
        
     /*   private String msg_;
        private final String tag_="aa_log";
   */ }
}
