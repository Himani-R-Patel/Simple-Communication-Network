-module(exchange).

-export([start/0]).
-export([get_tuples/1,master/0]).


start() ->
		  {ok, List} = file:consult("calls.txt"),
		  io:format("** Calls to be made **~n"),
		  
		  register( master, spawn(exchange , master ,[])),
		  get_tuples(List),
	      lists:foreach(fun(Tuple) -> master ! {Tuple} end , List),
 		  timer:sleep(1).
 		  

		
			
get_tuples([]) -> ok;

get_tuples([H|T]) ->
					 {Caller,Reciever} = H,
					 io:fwrite("~p : ",[Caller]),					
					 get_elements([Reciever]),
					 io:fwrite("~n"),
					 get_tuples(T).

								
get_elements([]) -> ok;
 
get_elements([H|T]) ->
						io:fwrite("~p", [H]),
						get_elements(T).
			 
master() ->
	
		receive
			
			{Tuple} -> 
					   {Caller, Reciever} = Tuple,
					   register(Caller,spawn(calling,child,[])),
						Caller ! {Caller, Reciever},					   
						master();
			
			{Caller,R,TimeStamp,Msg} ->
       									io:format("~p received ~s message from ~p [~p]~n",[R,Msg,Caller,TimeStamp]),
            							master();
			
			{Caller,R,TimeStamp,Msg,Msg} ->
				 							 io:format("~p received ~s message from ~p [~p]~n",[Caller,Msg,R,TimeStamp]),
           									 master()
 after
        1500 ->
	            {_, List} = file:consult("calls.txt"),          
			
				lists:foreach(fun(X) -> {S,_} = X,
				random:seed(),
			    timer:sleep(round(timer:seconds(random:uniform()))), S ! stop end, List),
                timer:sleep(500),
				io:format("~nMaster has received no replies for 1.5 seconds, ending...~n"),
	            exit(kill)
			

   end.
	







		  
    


	

 