        Jump         $$main                    
        DLabel       $eat-location-zero        
        DataZ        8                         
        DLabel       $print-format-integer     
        DataC        37                        %% "%d"
        DataC        100                       
        DataC        0                         
        DLabel       $print-format-floating    
        DataC        37                        %% "%f"
        DataC        102                       
        DataC        0                         
        DLabel       $print-format-character   
        DataC        37                        %% "%c"
        DataC        99                        
        DataC        0                         
        DLabel       $print-format-string      
        DataC        37                        %% "%s"
        DataC        115                       
        DataC        0                         
        DLabel       $print-format-boolean     
        DataC        37                        %% "%s"
        DataC        115                       
        DataC        0                         
        DLabel       $print-format-newline     
        DataC        10                        %% "\n"
        DataC        0                         
        DLabel       $print-format-space       
        DataC        32                        %% " "
        DataC        0                         
        DLabel       $boolean-true-string      
        DataC        116                       %% "true"
        DataC        114                       
        DataC        117                       
        DataC        101                       
        DataC        0                         
        DLabel       $boolean-false-string     
        DataC        102                       %% "false"
        DataC        97                        
        DataC        108                       
        DataC        115                       
        DataC        101                       
        DataC        0                         
        DLabel       $errors-general-message   
        DataC        82                        %% "Runtime error: %s\n"
        DataC        117                       
        DataC        110                       
        DataC        116                       
        DataC        105                       
        DataC        109                       
        DataC        101                       
        DataC        32                        
        DataC        101                       
        DataC        114                       
        DataC        114                       
        DataC        111                       
        DataC        114                       
        DataC        58                        
        DataC        32                        
        DataC        37                        
        DataC        115                       
        DataC        10                        
        DataC        0                         
        Label        $$general-runtime-error   
        PushD        $errors-general-message   
        Printf                                 
        Halt                                   
        DLabel       $errors-int-divide-by-zero 
        DataC        105                       %% "integer divide by zero"
        DataC        110                       
        DataC        116                       
        DataC        101                       
        DataC        103                       
        DataC        101                       
        DataC        114                       
        DataC        32                        
        DataC        100                       
        DataC        105                       
        DataC        118                       
        DataC        105                       
        DataC        100                       
        DataC        101                       
        DataC        32                        
        DataC        98                        
        DataC        121                       
        DataC        32                        
        DataC        122                       
        DataC        101                       
        DataC        114                       
        DataC        111                       
        DataC        0                         
        Label        $$i-divide-by-zero        
        PushD        $errors-int-divide-by-zero 
        Jump         $$general-runtime-error   
        DLabel       $errors-float-divide-by-zero 
        DataC        102                       %% "floating divide by zero"
        DataC        108                       
        DataC        111                       
        DataC        97                        
        DataC        116                       
        DataC        105                       
        DataC        110                       
        DataC        103                       
        DataC        32                        
        DataC        100                       
        DataC        105                       
        DataC        118                       
        DataC        105                       
        DataC        100                       
        DataC        101                       
        DataC        32                        
        DataC        98                        
        DataC        121                       
        DataC        32                        
        DataC        122                       
        DataC        101                       
        DataC        114                       
        DataC        111                       
        DataC        0                         
        Label        $$f-divide-by-zero        
        PushD        $errors-float-divide-by-zero 
        Jump         $$general-runtime-error   
        DLabel       $usable-memory-start      
        DLabel       $global-memory-block      
        DataZ        9                         
        Label        $$main                    
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% s
        DLabel       -string-1-ghjdfh \n nbsjh 
        DataI        3                         
        DataI        9                         
        DataI        15                        
        DataC        103                       %% "ghjdfh \n nbsjh"
        DataC        104                       
        DataC        106                       
        DataC        100                       
        DataC        102                       
        DataC        104                       
        DataC        32                        
        DataC        92                        
        DataC        110                       
        DataC        32                        
        DataC        110                       
        DataC        98                        
        DataC        115                       
        DataC        106                       
        DataC        104                       
        DataC        0                         
        PushD        -string-1-ghjdfh \n nbsjh 
        StoreI                                 
        PushD        $global-memory-block      
        PushI        4                         
        Add                                    %% t
        DLabel       -string-2-sfd098#varfgs   
        DataI        3                         
        DataI        9                         
        DataI        13                        
        DataC        115                       %% "sfd098#varfgs"
        DataC        102                       
        DataC        100                       
        DataC        48                        
        DataC        57                        
        DataC        56                        
        DataC        35                        
        DataC        118                       
        DataC        97                        
        DataC        114                       
        DataC        102                       
        DataC        103                       
        DataC        115                       
        DataC        0                         
        PushD        -string-2-sfd098#varfgs   
        StoreI                                 
        PushD        $global-memory-block      
        PushI        8                         
        Add                                    %% c
        PushI        115                       
        StoreC                                 
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% s
        LoadI                                  
        PushI        12                        
        Add                                    
        PushD        $print-format-string      
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        4                         
        Add                                    %% t
        LoadI                                  
        PushI        12                        
        Add                                    
        PushD        $print-format-string      
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        8                         
        Add                                    %% c
        LoadC                                  
        PushD        $print-format-character   
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Halt                                   
