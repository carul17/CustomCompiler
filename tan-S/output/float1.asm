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
        DLabel       $print-format-tab         
        DataC        9                         %% "\t"
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
        DataZ        93                        
        Label        $$main                    
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% r
        PushF        4.500000                  
        Nop                                    
        PStack                                 
        Label        -compare-1-arg2           
        Nop                                    
        PushF        0.500000                  
        Nop                                    
        PStack                                 
        Nop                                    
        Duplicate                              
        JumpFZero    $$f-divide-by-zero        
        FDivide                                
        StoreF                                 
        PushD        $global-memory-block      
        PushI        8                         
        Add                                    %% a
        PushF        4.500000                  
        Nop                                    
        PStack                                 
        Label        -compare-2-arg2           
        Nop                                    
        PushF        1.000000                  
        Nop                                    
        PStack                                 
        Nop                                    
        Duplicate                              
        JumpFZero    $$f-divide-by-zero        
        FDivide                                
        StoreF                                 
        PushD        $global-memory-block      
        PushI        16                        
        Add                                    %% b
        PushF        6.000000                  
        Nop                                    
        PStack                                 
        Label        -compare-3-arg2           
        Nop                                    
        PushF        2.000000                  
        Nop                                    
        PStack                                 
        Nop                                    
        Duplicate                              
        JumpFZero    $$f-divide-by-zero        
        FDivide                                
        StoreF                                 
        PushD        $global-memory-block      
        PushI        24                        
        Add                                    %% bb
        PushF        6.000000                  
        Nop                                    
        PStack                                 
        Label        -compare-4-arg2           
        PushF        2.000000                  
        Nop                                    
        PStack                                 
        FSubtract                              
        StoreF                                 
        PushD        $global-memory-block      
        PushI        32                        
        Add                                    %% bbb
        PushF        6.000000                  
        Nop                                    
        PStack                                 
        Label        -compare-5-arg2           
        PushF        2.000000                  
        Nop                                    
        PStack                                 
        FAdd                                   
        StoreF                                 
        PushD        $global-memory-block      
        PushI        40                        
        Add                                    %% s
        PushI        45                        
        Nop                                    
        PStack                                 
        Label        -compare-6-arg2           
        Nop                                    
        PushI        5                         
        Nop                                    
        PStack                                 
        Nop                                    
        Duplicate                              
        JumpFalse    $$i-divide-by-zero        
        Divide                                 
        StoreI                                 
        PushD        $global-memory-block      
        PushI        44                        
        Add                                    %% z
        PushI        45                        
        Nop                                    
        PStack                                 
        Label        -compare-7-arg2           
        Nop                                    
        PushI        1                         
        Nop                                    
        PStack                                 
        Nop                                    
        Duplicate                              
        JumpFalse    $$i-divide-by-zero        
        Divide                                 
        StoreI                                 
        PushD        $global-memory-block      
        PushI        48                        
        Add                                    %% y
        PushI        6                         
        Nop                                    
        PStack                                 
        Label        -compare-8-arg2           
        Nop                                    
        PushI        3                         
        Nop                                    
        PStack                                 
        Nop                                    
        Duplicate                              
        JumpFalse    $$i-divide-by-zero        
        Divide                                 
        StoreI                                 
        PushD        $global-memory-block      
        PushI        52                        
        Add                                    %% yy
        PushI        6                         
        Nop                                    
        PStack                                 
        Label        -compare-9-arg2           
        PushI        3                         
        Nop                                    
        PStack                                 
        Add                                    
        StoreI                                 
        PushD        $global-memory-block      
        PushI        56                        
        Add                                    %% neg
        PushI        -5                        
        StoreI                                 
        PushD        $global-memory-block      
        PushI        60                        
        Add                                    %% subneg
        PushI        6                         
        Nop                                    
        PStack                                 
        Label        -compare-10-arg2          
        PushI        -5                        
        Nop                                    
        PStack                                 
        Subtract                               
        StoreI                                 
        PushD        $global-memory-block      
        PushI        64                        
        Add                                    %% plusneg
        PushI        6                         
        Nop                                    
        PStack                                 
        Label        -compare-11-arg2          
        PushI        -5                        
        Nop                                    
        PStack                                 
        Add                                    
        StoreI                                 
        PushD        $global-memory-block      
        PushI        68                        
        Add                                    %% plusnegfloat
        PushF        6.200000                  
        Nop                                    
        PStack                                 
        Label        -compare-12-arg2          
        PushF        -5.100000                 
        Nop                                    
        PStack                                 
        FAdd                                   
        StoreF                                 
        PushD        $global-memory-block      
        PushI        76                        
        Add                                    %% plusplusfloat
        PushF        6.200000                  
        Nop                                    
        PStack                                 
        Label        -compare-13-arg2          
        PushF        5.100000                  
        Nop                                    
        PStack                                 
        FAdd                                   
        StoreF                                 
        PushD        $global-memory-block      
        PushI        84                        
        Add                                    %% x
        PushI        6                         
        Nop                                    
        PStack                                 
        Label        -compare-15-arg2          
        PushI        3                         
        Nop                                    
        PStack                                 
        Label        -compare-14-arg2          
        PushI        5                         
        Nop                                    
        PStack                                 
        Multiply                               
        Nop                                    
        PStack                                 
        Add                                    
        StoreI                                 
        PushD        $global-memory-block      
        PushI        88                        
        Add                                    %% compare
        Label        -compare-17-arg1          
        PushI        1                         
        Nop                                    
        PStack                                 
        Label        -compare-16-arg2          
        Label        -compare-17-arg2          
        PushI        0                         
        Nop                                    
        PStack                                 
        Label        -compare-17-sub           
        Subtract                               
        JumpTrue     -compare-17-true          
        Jump         -compare-17-false         
        Label        -compare-17-true          
        PushI        1                         
        Jump         -compare-17-join          
        Label        -compare-17-false         
        PushI        0                         
        Jump         -compare-17-join          
        Label        -compare-17-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        89                        
        Add                                    %% word
        DLabel       -string-18-test           
        DataI        3                         
        DataI        9                         
        DataI        4                         
        DataC        116                       %% "test"
        DataC        101                       
        DataC        115                       
        DataC        116                       
        DataC        0                         
        PushD        -string-18-test           
        StoreI                                 
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% r
        LoadF                                  
        PushD        $print-format-floating    
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        8                         
        Add                                    %% a
        LoadF                                  
        PushD        $print-format-floating    
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        16                        
        Add                                    %% b
        LoadF                                  
        PushD        $print-format-floating    
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        24                        
        Add                                    %% bb
        LoadF                                  
        PushD        $print-format-floating    
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        32                        
        Add                                    %% bbb
        LoadF                                  
        PushD        $print-format-floating    
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        40                        
        Add                                    %% s
        LoadI                                  
        PushD        $print-format-integer     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        44                        
        Add                                    %% z
        LoadI                                  
        PushD        $print-format-integer     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        48                        
        Add                                    %% y
        LoadI                                  
        PushD        $print-format-integer     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        52                        
        Add                                    %% yy
        LoadI                                  
        PushD        $print-format-integer     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        84                        
        Add                                    %% x
        LoadI                                  
        PushD        $print-format-integer     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        56                        
        Add                                    %% neg
        LoadI                                  
        PushD        $print-format-integer     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        60                        
        Add                                    %% subneg
        LoadI                                  
        PushD        $print-format-integer     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        64                        
        Add                                    %% plusneg
        LoadI                                  
        PushD        $print-format-integer     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        68                        
        Add                                    %% plusnegfloat
        LoadF                                  
        PushD        $print-format-floating    
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        76                        
        Add                                    %% plusplusfloat
        LoadF                                  
        PushD        $print-format-floating    
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        88                        
        Add                                    %% compare
        LoadC                                  
        JumpTrue     -print-boolean-19-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-19-join    
        Label        -print-boolean-19-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-19-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        89                        
        Add                                    %% word
        LoadI                                  
        PushI        12                        
        Add                                    
        PushD        $print-format-string      
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Halt                                   
