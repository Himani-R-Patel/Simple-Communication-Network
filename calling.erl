 -module(calling).
-export([child/0,get_timestamp/0]).

get_timestamp() ->
    {_,_,MicroSecond} = erlang:now(),
    MicroSecond.

child() ->
	receive
			{Caller, Reciever} -> 	
								  						  
								  Msg = "intro",								   
				                  lists:foreach(fun(R) ->  TimeStamp = get_timestamp(), R  ! {Caller,R,TimeStamp,Msg}   end , Reciever ),
								  child();
	
	        {Caller,R,TimeStamp,Msg} ->
			 						    random:seed(),
										timer:sleep(round(timer:seconds(random:uniform()))),
										master ! {Caller,R,TimeStamp,Msg},
										RS = "reply",
										Caller ! {Caller,R,TimeStamp,RS,RS},
										child();
															 						
			 {Caller,R,TimeStamp,Msg,Msg} ->
											random:seed(),
											timer:sleep(round(timer:seconds(random:uniform()))),
											master ! {Caller,R,TimeStamp,Msg,Msg},
											child();
				 stop ->
						     {_,P} = erlang:process_info(self(), registered_name),
				            io:format("~nProcess ~p has received no calls for 1 second, ending...~n~n",[P]),
				            exit(kill)
    end.
    


