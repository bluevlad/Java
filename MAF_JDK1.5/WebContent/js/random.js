    function rndlist(max, cnt){
        var numbers = rnd(max, cnt);
        var num;

        if(numbers.length>0){
            num = numbers[0];
            for(var i=1; i<numbers.length; i++){
                num += "#"+numbers[i];
            }
        }else if(numbers>0){
            num = numbers;
        }
        
        return num;
    }

    /*
            1 ~ max : select n
            selfmotive
    */

    function rnd(max, cnt){
        retval = new Array();
        rndval = new Array();

        var dble = 2;
        var loop = true;
        var len, sw

        //-----------------------------
        if(max<cnt){
            alert("max < cnt");
            return false;

           }else if(cnt<=0){
                  retval[0] = 0;
                     loop = false;

        }else if(max<=0){
            alert("최대 값은 0보다 커야합니다   ");
            return false;
        }

        //-----------------------------
        while(loop){
            rndval = create_value(max, dble);

            if(retval[0]=="undefined"){
                retval[0] = rndval[0];
            }

            for(var k=0; k<(max*dble); k++){
                len = retval.length
                sw = true;

                for(var i=0; i<len; i++){
                    if(rndval[k]==retval[i]){
                        sw = false;
                       }
                }

                if(sw){
                    retval[len] = rndval[k];
                   }

                if(retval.length>=cnt){
                    loop = false;
                    break;
                } //end if

            } //end for

        } //end while

        return retval;

    }

    function create_value(max, dble){
        rndval = new Array();

        for(var i=0; i<(max*dble); i++){
            rndval[i] = (parseInt(Math.random() * 10000) % max)+1;
        }

        return rndval
    }