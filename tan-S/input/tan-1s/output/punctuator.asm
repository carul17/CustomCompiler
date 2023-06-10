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
        DataZ        36                        
        Label        $$main                    
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        PushI        97                        
        StoreC                                 
        PushD        $global-memory-block      
        PushI        1                         
        Add                                    %% b
        PushI        97                        
        StoreC                                 
        PushD        $global-memory-block      
        PushI        2                         
        Add                                    %% c
        PushD        $global-memory-block      
        PushI        1                         
        Add                                    %% b
        LoadC                                  
        StoreC                                 
        PushD        $global-memory-block      
        PushI        3                         
        Add                                    %% d
        PushI        97                        
        StoreC                                 
        PushD        $global-memory-block      
        PushI        4                         
        Add                                    %% e
        PushI        100                       
        StoreC                                 
        PushD        $global-memory-block      
        PushI        4                         
        Add                                    %% e
        PushD        $global-memory-block      
        PushI        3                         
        Add                                    %% d
        LoadC                                  
        StoreC                                 
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% s
        DLabel       -string-1-abc             
        DataI        3                         
        DataI        9                         
        DataI        3                         
        DataC        97                        %% "abc"
        DataC        98                        
        DataC        99                        
        DataC        0                         
        PushD        -string-1-abc             
        StoreI                                 
        PushD        $global-memory-block      
        PushI        9                         
        Add                                    %% s2
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% s
        LoadI                                  
        StoreI                                 
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% s
        DLabel       -string-2-bde             
        DataI        3                         
        DataI        9                         
        DataI        3                         
        DataC        98                        %% "bde"
        DataC        100                       
        DataC        101                       
        DataC        0                         
        PushD        -string-2-bde             
        StoreI                                 
        PushD        $global-memory-block      
        PushI        13                        
        Add                                    %% s3
        DLabel       -string-3-abc             
        DataI        3                         
        DataI        9                         
        DataI        3                         
        DataC        97                        %% "abc"
        DataC        98                        
        DataC        99                        
        DataC        0                         
        PushD        -string-3-abc             
        StoreI                                 
        PushD        $global-memory-block      
        PushI        17                        
        Add                                    %% s4
        PushD        $global-memory-block      
        PushI        9                         
        Add                                    %% s2
        LoadI                                  
        StoreI                                 
        PushD        $global-memory-block      
        PushI        21                        
        Add                                    %% thing
        Label        -compare-7-arg1           
        DLabel       -string-4-abc             
        DataI        3                         
        DataI        9                         
        DataI        3                         
        DataC        97                        %% "abc"
        DataC        98                        
        DataC        99                        
        DataC        0                         
        PushD        -string-4-abc             
        Label        -compare-6-arg2           
        Label        -compare-7-arg2           
        DLabel       -string-5-abc             
        DataI        3                         
        DataI        9                         
        DataI        3                         
        DataC        97                        %% "abc"
        DataC        98                        
        DataC        99                        
        DataC        0                         
        PushD        -string-5-abc             
        Label        -compare-7-sub            
        Subtract                               
        JumpFalse    -compare-7-true           
        Jump         -compare-7-false          
        Label        -compare-7-true           
        PushI        1                         
        Jump         -compare-7-join           
        Label        -compare-7-false          
        PushI        0                         
        Jump         -compare-7-join           
        Label        -compare-7-join           
        StoreC                                 
        PushD        $global-memory-block      
        PushI        22                        
        Add                                    %% pos2
        Label        -compare-9-arg1           
        PushI        2                         
        Label        -compare-8-arg2           
        Label        -compare-9-arg2           
        PushI        1                         
        Label        -compare-9-sub            
        Subtract                               
        JumpPos      -compare-9-true           
        Jump         -compare-9-false          
        Label        -compare-9-true           
        PushI        1                         
        Jump         -compare-9-join           
        Label        -compare-9-false          
        PushI        0                         
        Jump         -compare-9-join           
        Label        -compare-9-join           
        StoreC                                 
        PushD        $global-memory-block      
        PushI        23                        
        Add                                    %% pos3
        Label        -compare-11-arg1          
        PushI        2                         
        Label        -compare-10-arg2          
        Label        -compare-11-arg2          
        PushI        1                         
        Label        -compare-11-sub           
        Subtract                               
        JumpNeg      -compare-11-true          
        Jump         -compare-11-false         
        Label        -compare-11-true          
        PushI        1                         
        Jump         -compare-11-join          
        Label        -compare-11-false         
        PushI        0                         
        Jump         -compare-11-join          
        Label        -compare-11-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        24                        
        Add                                    %% pos4
        Label        -compare-13-arg1          
        PushI        2                         
        Label        -compare-12-arg2          
        Label        -compare-13-arg2          
        PushI        1                         
        Label        -compare-13-sub           
        Subtract                               
        Duplicate                              
        JumpNeg      -compare-13-temp          
        JumpFalse    -compare-13-true          
        Jump         -compare-13-false         
        Label        -compare-13-temp          
        Pop                                    
        Jump         -compare-13-true          
        Label        -compare-13-true          
        PushI        1                         
        Jump         -compare-13-join          
        Label        -compare-13-false         
        PushI        0                         
        Jump         -compare-13-join          
        Label        -compare-13-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        25                        
        Add                                    %% pos5
        Label        -compare-15-arg1          
        PushI        2                         
        Label        -compare-14-arg2          
        Label        -compare-15-arg2          
        PushI        1                         
        Label        -compare-15-sub           
        Subtract                               
        Duplicate                              
        JumpPos      -compare-15-temp          
        JumpFalse    -compare-15-true          
        Jump         -compare-15-false         
        Label        -compare-15-temp          
        Pop                                    
        Jump         -compare-15-true          
        Label        -compare-15-true          
        PushI        1                         
        Jump         -compare-15-join          
        Label        -compare-15-false         
        PushI        0                         
        Jump         -compare-15-join          
        Label        -compare-15-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        26                        
        Add                                    %% pos6
        Label        -compare-17-arg1          
        PushI        2                         
        Label        -compare-16-arg2          
        Label        -compare-17-arg2          
        PushI        2                         
        Label        -compare-17-sub           
        Subtract                               
        Duplicate                              
        JumpPos      -compare-17-temp          
        JumpFalse    -compare-17-true          
        Jump         -compare-17-false         
        Label        -compare-17-temp          
        Pop                                    
        Jump         -compare-17-true          
        Label        -compare-17-true          
        PushI        1                         
        Jump         -compare-17-join          
        Label        -compare-17-false         
        PushI        0                         
        Jump         -compare-17-join          
        Label        -compare-17-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        27                        
        Add                                    %% pos7
        Label        -compare-19-arg1          
        PushI        2                         
        Label        -compare-18-arg2          
        Label        -compare-19-arg2          
        PushI        2                         
        Label        -compare-19-sub           
        Subtract                               
        Duplicate                              
        JumpNeg      -compare-19-temp          
        JumpFalse    -compare-19-true          
        Jump         -compare-19-false         
        Label        -compare-19-temp          
        Pop                                    
        Jump         -compare-19-true          
        Label        -compare-19-true          
        PushI        1                         
        Jump         -compare-19-join          
        Label        -compare-19-false         
        PushI        0                         
        Jump         -compare-19-join          
        Label        -compare-19-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        28                        
        Add                                    %% pos8
        Label        -compare-21-arg1          
        PushI        2                         
        Label        -compare-20-arg2          
        Label        -compare-21-arg2          
        PushI        2                         
        Label        -compare-21-sub           
        Subtract                               
        JumpFalse    -compare-21-true          
        Jump         -compare-21-false         
        Label        -compare-21-true          
        PushI        1                         
        Jump         -compare-21-join          
        Label        -compare-21-false         
        PushI        0                         
        Jump         -compare-21-join          
        Label        -compare-21-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        29                        
        Add                                    %% pos9
        Label        -compare-23-arg1          
        PushI        2                         
        Label        -compare-22-arg2          
        Label        -compare-23-arg2          
        PushI        3                         
        Label        -compare-23-sub           
        Subtract                               
        JumpFalse    -compare-23-true          
        Jump         -compare-23-false         
        Label        -compare-23-true          
        PushI        1                         
        Jump         -compare-23-join          
        Label        -compare-23-false         
        PushI        0                         
        Jump         -compare-23-join          
        Label        -compare-23-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        30                        
        Add                                    %% pos10
        Label        -compare-25-arg1          
        PushI        2                         
        Label        -compare-24-arg2          
        Label        -compare-25-arg2          
        PushI        2                         
        Label        -compare-25-sub           
        Subtract                               
        JumpTrue     -compare-25-true          
        Jump         -compare-25-false         
        Label        -compare-25-true          
        PushI        1                         
        Jump         -compare-25-join          
        Label        -compare-25-false         
        PushI        0                         
        Jump         -compare-25-join          
        Label        -compare-25-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        31                        
        Add                                    %% pos11
        Label        -compare-27-arg1          
        PushI        2                         
        Label        -compare-26-arg2          
        Label        -compare-27-arg2          
        PushI        3                         
        Label        -compare-27-sub           
        Subtract                               
        JumpTrue     -compare-27-true          
        Jump         -compare-27-false         
        Label        -compare-27-true          
        PushI        1                         
        Jump         -compare-27-join          
        Label        -compare-27-false         
        PushI        0                         
        Jump         -compare-27-join          
        Label        -compare-27-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        32                        
        Add                                    %% pos12
        Label        -compare-29-arg1          
        PushI        1                         
        Label        -compare-28-arg2          
        Label        -compare-29-arg2          
        PushI        1                         
        Label        -compare-29-sub           
        Subtract                               
        JumpTrue     -compare-29-true          
        Jump         -compare-29-false         
        Label        -compare-29-true          
        PushI        1                         
        Jump         -compare-29-join          
        Label        -compare-29-false         
        PushI        0                         
        Jump         -compare-29-join          
        Label        -compare-29-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        33                        
        Add                                    %% pos13
        Label        -compare-31-arg1          
        PushI        1                         
        Label        -compare-30-arg2          
        Label        -compare-31-arg2          
        PushI        0                         
        Label        -compare-31-sub           
        Subtract                               
        JumpTrue     -compare-31-true          
        Jump         -compare-31-false         
        Label        -compare-31-true          
        PushI        1                         
        Jump         -compare-31-join          
        Label        -compare-31-false         
        PushI        0                         
        Jump         -compare-31-join          
        Label        -compare-31-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        34                        
        Add                                    %% pos14
        Label        -compare-33-arg1          
        PushI        1                         
        Label        -compare-32-arg2          
        Label        -compare-33-arg2          
        PushI        1                         
        Label        -compare-33-sub           
        Subtract                               
        JumpFalse    -compare-33-true          
        Jump         -compare-33-false         
        Label        -compare-33-true          
        PushI        1                         
        Jump         -compare-33-join          
        Label        -compare-33-false         
        PushI        0                         
        Jump         -compare-33-join          
        Label        -compare-33-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        35                        
        Add                                    %% pos15
        Label        -compare-35-arg1          
        PushI        1                         
        Label        -compare-34-arg2          
        Label        -compare-35-arg2          
        PushI        0                         
        Label        -compare-35-sub           
        Subtract                               
        JumpFalse    -compare-35-true          
        Jump         -compare-35-false         
        Label        -compare-35-true          
        PushI        1                         
        Jump         -compare-35-join          
        Label        -compare-35-false         
        PushI        0                         
        Jump         -compare-35-join          
        Label        -compare-35-join          
        StoreC                                 
        Label        -compare-37-arg1          
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        Label        -compare-36-arg2          
        LoadC                                  
        Label        -compare-37-arg2          
        PushD        $global-memory-block      
        PushI        1                         
        Add                                    %% b
        LoadC                                  
        Label        -compare-37-sub           
        Subtract                               
        JumpFalse    -compare-37-true          
        Jump         -compare-37-false         
        Label        -compare-37-true          
        PushI        1                         
        Jump         -compare-37-join          
        Label        -compare-37-false         
        PushI        0                         
        Jump         -compare-37-join          
        Label        -compare-37-join          
        JumpTrue     -print-boolean-38-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-38-join    
        Label        -print-boolean-38-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-38-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-40-arg1          
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        Label        -compare-39-arg2          
        LoadC                                  
        Label        -compare-40-arg2          
        PushD        $global-memory-block      
        PushI        2                         
        Add                                    %% c
        LoadC                                  
        Label        -compare-40-sub           
        Subtract                               
        JumpFalse    -compare-40-true          
        Jump         -compare-40-false         
        Label        -compare-40-true          
        PushI        1                         
        Jump         -compare-40-join          
        Label        -compare-40-false         
        PushI        0                         
        Jump         -compare-40-join          
        Label        -compare-40-join          
        JumpTrue     -print-boolean-41-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-41-join    
        Label        -print-boolean-41-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-41-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-43-arg1          
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        Label        -compare-42-arg2          
        LoadC                                  
        Label        -compare-43-arg2          
        PushD        $global-memory-block      
        PushI        3                         
        Add                                    %% d
        LoadC                                  
        Label        -compare-43-sub           
        Subtract                               
        JumpFalse    -compare-43-true          
        Jump         -compare-43-false         
        Label        -compare-43-true          
        PushI        1                         
        Jump         -compare-43-join          
        Label        -compare-43-false         
        PushI        0                         
        Jump         -compare-43-join          
        Label        -compare-43-join          
        JumpTrue     -print-boolean-44-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-44-join    
        Label        -print-boolean-44-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-44-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-46-arg1          
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        Label        -compare-45-arg2          
        LoadC                                  
        Label        -compare-46-arg2          
        PushD        $global-memory-block      
        PushI        4                         
        Add                                    %% e
        LoadC                                  
        Label        -compare-46-sub           
        Subtract                               
        JumpFalse    -compare-46-true          
        Jump         -compare-46-false         
        Label        -compare-46-true          
        PushI        1                         
        Jump         -compare-46-join          
        Label        -compare-46-false         
        PushI        0                         
        Jump         -compare-46-join          
        Label        -compare-46-join          
        JumpTrue     -print-boolean-47-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-47-join    
        Label        -print-boolean-47-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-47-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        DLabel       -string-48-strings ////   
        DataI        3                         
        DataI        9                         
        DataI        12                        
        DataC        115                       %% "strings ////"
        DataC        116                       
        DataC        114                       
        DataC        105                       
        DataC        110                       
        DataC        103                       
        DataC        115                       
        DataC        32                        
        DataC        47                        
        DataC        47                        
        DataC        47                        
        DataC        47                        
        DataC        0                         
        PushD        -string-48-strings ////   
        PushI        12                        
        Add                                    
        PushD        $print-format-string      
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-50-arg1          
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% s
        Label        -compare-49-arg2          
        LoadI                                  
        Label        -compare-50-arg2          
        PushD        $global-memory-block      
        PushI        9                         
        Add                                    %% s2
        LoadI                                  
        Label        -compare-50-sub           
        Subtract                               
        JumpFalse    -compare-50-true          
        Jump         -compare-50-false         
        Label        -compare-50-true          
        PushI        1                         
        Jump         -compare-50-join          
        Label        -compare-50-false         
        PushI        0                         
        Jump         -compare-50-join          
        Label        -compare-50-join          
        JumpTrue     -print-boolean-51-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-51-join    
        Label        -print-boolean-51-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-51-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        9                         
        Add                                    %% s2
        LoadI                                  
        PushI        12                        
        Add                                    
        PushD        $print-format-string      
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-53-arg1          
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% s
        Label        -compare-52-arg2          
        LoadI                                  
        Label        -compare-53-arg2          
        PushD        $global-memory-block      
        PushI        13                        
        Add                                    %% s3
        LoadI                                  
        Label        -compare-53-sub           
        Subtract                               
        JumpFalse    -compare-53-true          
        Jump         -compare-53-false         
        Label        -compare-53-true          
        PushI        1                         
        Jump         -compare-53-join          
        Label        -compare-53-false         
        PushI        0                         
        Jump         -compare-53-join          
        Label        -compare-53-join          
        JumpTrue     -print-boolean-54-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-54-join    
        Label        -print-boolean-54-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-54-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-56-arg1          
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% s
        Label        -compare-55-arg2          
        LoadI                                  
        Label        -compare-56-arg2          
        PushD        $global-memory-block      
        PushI        17                        
        Add                                    %% s4
        LoadI                                  
        Label        -compare-56-sub           
        Subtract                               
        JumpFalse    -compare-56-true          
        Jump         -compare-56-false         
        Label        -compare-56-true          
        PushI        1                         
        Jump         -compare-56-join          
        Label        -compare-56-false         
        PushI        0                         
        Jump         -compare-56-join          
        Label        -compare-56-join          
        JumpTrue     -print-boolean-57-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-57-join    
        Label        -print-boolean-57-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-57-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% s
        LoadI                                  
        PushI        12                        
        Add                                    
        PushD        $print-format-string      
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $print-format-tab         
        Printf                                 
        PushD        $global-memory-block      
        PushI        17                        
        Add                                    %% s4
        LoadI                                  
        PushI        12                        
        Add                                    
        PushD        $print-format-string      
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        21                        
        Add                                    %% thing
        LoadC                                  
        JumpTrue     -print-boolean-58-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-58-join    
        Label        -print-boolean-58-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-58-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushI        5                         
        PushD        $print-format-integer     
        Printf                                 
        PushD        $print-format-tab         
        Printf                                 
        DLabel       -string-59-end strings /// 
        DataI        3                         
        DataI        9                         
        DataI        15                        
        DataC        101                       %% "end strings ///"
        DataC        110                       
        DataC        100                       
        DataC        32                        
        DataC        115                       
        DataC        116                       
        DataC        114                       
        DataC        105                       
        DataC        110                       
        DataC        103                       
        DataC        115                       
        DataC        32                        
        DataC        47                        
        DataC        47                        
        DataC        47                        
        DataC        0                         
        PushD        -string-59-end strings /// 
        PushI        12                        
        Add                                    
        PushD        $print-format-string      
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        22                        
        Add                                    %% pos2
        LoadC                                  
        JumpTrue     -print-boolean-60-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-60-join    
        Label        -print-boolean-60-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-60-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        23                        
        Add                                    %% pos3
        LoadC                                  
        JumpTrue     -print-boolean-61-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-61-join    
        Label        -print-boolean-61-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-61-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        24                        
        Add                                    %% pos4
        LoadC                                  
        JumpTrue     -print-boolean-62-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-62-join    
        Label        -print-boolean-62-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-62-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        25                        
        Add                                    %% pos5
        LoadC                                  
        JumpTrue     -print-boolean-63-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-63-join    
        Label        -print-boolean-63-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-63-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        26                        
        Add                                    %% pos6
        LoadC                                  
        JumpTrue     -print-boolean-64-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-64-join    
        Label        -print-boolean-64-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-64-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        27                        
        Add                                    %% pos7
        LoadC                                  
        JumpTrue     -print-boolean-65-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-65-join    
        Label        -print-boolean-65-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-65-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        28                        
        Add                                    %% pos8
        LoadC                                  
        JumpTrue     -print-boolean-66-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-66-join    
        Label        -print-boolean-66-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-66-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        29                        
        Add                                    %% pos9
        LoadC                                  
        JumpTrue     -print-boolean-67-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-67-join    
        Label        -print-boolean-67-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-67-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        30                        
        Add                                    %% pos10
        LoadC                                  
        JumpTrue     -print-boolean-68-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-68-join    
        Label        -print-boolean-68-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-68-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        31                        
        Add                                    %% pos11
        LoadC                                  
        JumpTrue     -print-boolean-69-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-69-join    
        Label        -print-boolean-69-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-69-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        32                        
        Add                                    %% pos12
        LoadC                                  
        JumpTrue     -print-boolean-70-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-70-join    
        Label        -print-boolean-70-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-70-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        33                        
        Add                                    %% pos13
        LoadC                                  
        JumpTrue     -print-boolean-71-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-71-join    
        Label        -print-boolean-71-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-71-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        34                        
        Add                                    %% pos14
        LoadC                                  
        JumpTrue     -print-boolean-72-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-72-join    
        Label        -print-boolean-72-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-72-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        35                        
        Add                                    %% pos15
        LoadC                                  
        JumpTrue     -print-boolean-73-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-73-join    
        Label        -print-boolean-73-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-73-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Halt                                   
