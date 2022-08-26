s = "ababcdcdababcdcd";
    var answer = 0;
    var count = 1;
    var i = 0;
    var len = s.length;
    var min = s.length;
    
    
    while(count <= len) {
        var encoding = [];
        var data = {};
        while(i < len) {
           
            var str = s.substr(i,count);
            var nextstr = s.substr(i+1,count);
            if(data[str] == null) {
                data[str] = 0;
            }
            data[str] +=1;
            
            if(str !== nextstr) {
                if(data[str] != 1) {
                    encoding += (str + data[str]);
                } else {
                    encoding += str;
                }
                
                data = {};
            }
            i++;
        }
        ++count;
        
        if(encoding.length != 0) {
            min = (min < encoding.length) ? min : encoding.length;
        }
 
    }