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
        DataZ        35                        
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
        Add                                    %% pos2
        Label        -compare-5-arg1           
        PushI        2                         
        Label        -compare-4-arg2           
        Label        -compare-5-arg2           
        PushI        1                         
        Label        -compare-5-sub            
        Subtract                               
        JumpPos      -compare-5-true           
        Jump         -compare-5-false          
        Label        -compare-5-true           
        PushI        1                         
        Jump         -compare-5-join           
        Label        -compare-5-false          
        PushI        0                         
        Jump         -compare-5-join           
        Label        -compare-5-join           
        StoreC                                 
        PushD        $global-memory-block      
        PushI        22                        
        Add                                    %% pos3
        Label        -compare-7-arg1           
        PushI        2                         
        Label        -compare-6-arg2           
        Label        -compare-7-arg2           
        PushI        1                         
        Label        -compare-7-sub            
        Subtract                               
        JumpNeg      -compare-7-true           
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
        PushI        23                        
        Add                                    %% pos4
        Label        -compare-9-arg1           
        PushI        2                         
        Label        -compare-8-arg2           
        Label        -compare-9-arg2           
        PushI        1                         
        Label        -compare-9-sub            
        Subtract                               
        Duplicate                              
        JumpNeg      -compare-9-temp           
        JumpFalse    -compare-9-true           
        Jump         -compare-9-false          
        Label        -compare-9-temp           
        Pop                                    
        Jump         -compare-9-true           
        Label        -compare-9-true           
        PushI        1                         
        Jump         -compare-9-join           
        Label        -compare-9-false          
        PushI        0                         
        Jump         -compare-9-join           
        Label        -compare-9-join           
        StoreC                                 
        PushD        $global-memory-block      
        PushI        24                        
        Add                                    %% pos5
        Label        -compare-11-arg1          
        PushI        2                         
        Label        -compare-10-arg2          
        Label        -compare-11-arg2          
        PushI        1                         
        Label        -compare-11-sub           
        Subtract                               
        Duplicate                              
        JumpPos      -compare-11-temp          
        JumpFalse    -compare-11-true          
        Jump         -compare-11-false         
        Label        -compare-11-temp          
        Pop                                    
        Jump         -compare-11-true          
        Label        -compare-11-true          
        PushI        1                         
        Jump         -compare-11-join          
        Label        -compare-11-false         
        PushI        0                         
        Jump         -compare-11-join          
        Label        -compare-11-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        25                        
        Add                                    %% pos6
        Label        -compare-13-arg1          
        PushI        2                         
        Label        -compare-12-arg2          
        Label        -compare-13-arg2          
        PushI        2                         
        Label        -compare-13-sub           
        Subtract                               
        Duplicate                              
        JumpPos      -compare-13-temp          
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
        PushI        26                        
        Add                                    %% pos7
        Label        -compare-15-arg1          
        PushI        2                         
        Label        -compare-14-arg2          
        Label        -compare-15-arg2          
        PushI        2                         
        Label        -compare-15-sub           
        Subtract                               
        Duplicate                              
        JumpNeg      -compare-15-temp          
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
        PushI        27                        
        Add                                    %% pos8
        Label        -compare-17-arg1          
        PushI        2                         
        Label        -compare-16-arg2          
        Label        -compare-17-arg2          
        PushI        2                         
        Label        -compare-17-sub           
        Subtract                               
        JumpFalse    -compare-17-true          
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
        PushI        28                        
        Add                                    %% pos9
        Label        -compare-19-arg1          
        PushI        2                         
        Label        -compare-18-arg2          
        Label        -compare-19-arg2          
        PushI        3                         
        Label        -compare-19-sub           
        Subtract                               
        JumpFalse    -compare-19-true          
        Jump         -compare-19-false         
        Label        -compare-19-true          
        PushI        1                         
        Jump         -compare-19-join          
        Label        -compare-19-false         
        PushI        0                         
        Jump         -compare-19-join          
        Label        -compare-19-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        29                        
        Add                                    %% pos10
        Label        -compare-21-arg1          
        PushI        2                         
        Label        -compare-20-arg2          
        Label        -compare-21-arg2          
        PushI        2                         
        Label        -compare-21-sub           
        Subtract                               
        JumpTrue     -compare-21-true          
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
        PushI        30                        
        Add                                    %% pos11
        Label        -compare-23-arg1          
        PushI        2                         
        Label        -compare-22-arg2          
        Label        -compare-23-arg2          
        PushI        3                         
        Label        -compare-23-sub           
        Subtract                               
        JumpTrue     -compare-23-true          
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
        PushI        31                        
        Add                                    %% pos12
        Label        -compare-25-arg1          
        PushI        1                         
        Label        -compare-24-arg2          
        Label        -compare-25-arg2          
        PushI        1                         
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
        PushI        32                        
        Add                                    %% pos13
        Label        -compare-27-arg1          
        PushI        1                         
        Label        -compare-26-arg2          
        Label        -compare-27-arg2          
        PushI        0                         
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
        PushI        33                        
        Add                                    %% pos14
        Label        -compare-29-arg1          
        PushI        1                         
        Label        -compare-28-arg2          
        Label        -compare-29-arg2          
        PushI        1                         
        Label        -compare-29-sub           
        Subtract                               
        JumpFalse    -compare-29-true          
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
        PushI        34                        
        Add                                    %% pos15
        Label        -compare-31-arg1          
        PushI        1                         
        Label        -compare-30-arg2          
        Label        -compare-31-arg2          
        PushI        0                         
        Label        -compare-31-sub           
        Subtract                               
        JumpFalse    -compare-31-true          
        Jump         -compare-31-false         
        Label        -compare-31-true          
        PushI        1                         
        Jump         -compare-31-join          
        Label        -compare-31-false         
        PushI        0                         
        Jump         -compare-31-join          
        Label        -compare-31-join          
        StoreC                                 
        Label        -compare-33-arg1          
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        Label        -compare-32-arg2          
        LoadC                                  
        Label        -compare-33-arg2          
        PushD        $global-memory-block      
        PushI        1                         
        Add                                    %% b
        LoadC                                  
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
        JumpTrue     -print-boolean-34-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-34-join    
        Label        -print-boolean-34-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-34-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-36-arg1          
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        Label        -compare-35-arg2          
        LoadC                                  
        Label        -compare-36-arg2          
        PushD        $global-memory-block      
        PushI        2                         
        Add                                    %% c
        LoadC                                  
        Label        -compare-36-sub           
        Subtract                               
        JumpFalse    -compare-36-true          
        Jump         -compare-36-false         
        Label        -compare-36-true          
        PushI        1                         
        Jump         -compare-36-join          
        Label        -compare-36-false         
        PushI        0                         
        Jump         -compare-36-join          
        Label        -compare-36-join          
        JumpTrue     -print-boolean-37-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-37-join    
        Label        -print-boolean-37-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-37-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-39-arg1          
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        Label        -compare-38-arg2          
        LoadC                                  
        Label        -compare-39-arg2          
        PushD        $global-memory-block      
        PushI        3                         
        Add                                    %% d
        LoadC                                  
        Label        -compare-39-sub           
        Subtract                               
        JumpFalse    -compare-39-true          
        Jump         -compare-39-false         
        Label        -compare-39-true          
        PushI        1                         
        Jump         -compare-39-join          
        Label        -compare-39-false         
        PushI        0                         
        Jump         -compare-39-join          
        Label        -compare-39-join          
        JumpTrue     -print-boolean-40-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-40-join    
        Label        -print-boolean-40-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-40-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-42-arg1          
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        Label        -compare-41-arg2          
        LoadC                                  
        Label        -compare-42-arg2          
        PushD        $global-memory-block      
        PushI        4                         
        Add                                    %% e
        LoadC                                  
        Label        -compare-42-sub           
        Subtract                               
        JumpFalse    -compare-42-true          
        Jump         -compare-42-false         
        Label        -compare-42-true          
        PushI        1                         
        Jump         -compare-42-join          
        Label        -compare-42-false         
        PushI        0                         
        Jump         -compare-42-join          
        Label        -compare-42-join          
        JumpTrue     -print-boolean-43-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-43-join    
        Label        -print-boolean-43-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-43-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-45-arg1          
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% s
        Label        -compare-44-arg2          
        LoadI                                  
        Label        -compare-45-arg2          
        PushD        $global-memory-block      
        PushI        9                         
        Add                                    %% s2
        LoadI                                  
        Label        -compare-45-sub           
        Subtract                               
        JumpFalse    -compare-45-true          
        Jump         -compare-45-false         
        Label        -compare-45-true          
        PushI        1                         
        Jump         -compare-45-join          
        Label        -compare-45-false         
        PushI        0                         
        Jump         -compare-45-join          
        Label        -compare-45-join          
        JumpTrue     -print-boolean-46-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-46-join    
        Label        -print-boolean-46-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-46-join    
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
        Label        -compare-48-arg1          
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% s
        Label        -compare-47-arg2          
        LoadI                                  
        Label        -compare-48-arg2          
        PushD        $global-memory-block      
        PushI        13                        
        Add                                    %% s3
        LoadI                                  
        Label        -compare-48-sub           
        Subtract                               
        JumpFalse    -compare-48-true          
        Jump         -compare-48-false         
        Label        -compare-48-true          
        PushI        1                         
        Jump         -compare-48-join          
        Label        -compare-48-false         
        PushI        0                         
        Jump         -compare-48-join          
        Label        -compare-48-join          
        JumpTrue     -print-boolean-49-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-49-join    
        Label        -print-boolean-49-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-49-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-51-arg1          
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% s
        Label        -compare-50-arg2          
        LoadI                                  
        Label        -compare-51-arg2          
        PushD        $global-memory-block      
        PushI        17                        
        Add                                    %% s4
        LoadI                                  
        Label        -compare-51-sub           
        Subtract                               
        JumpFalse    -compare-51-true          
        Jump         -compare-51-false         
        Label        -compare-51-true          
        PushI        1                         
        Jump         -compare-51-join          
        Label        -compare-51-false         
        PushI        0                         
        Jump         -compare-51-join          
        Label        -compare-51-join          
        JumpTrue     -print-boolean-52-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-52-join    
        Label        -print-boolean-52-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-52-join    
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
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        21                        
        Add                                    %% pos2
        LoadC                                  
        JumpTrue     -print-boolean-53-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-53-join    
        Label        -print-boolean-53-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-53-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        22                        
        Add                                    %% pos3
        LoadC                                  
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
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        23                        
        Add                                    %% pos4
        LoadC                                  
        JumpTrue     -print-boolean-55-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-55-join    
        Label        -print-boolean-55-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-55-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        24                        
        Add                                    %% pos5
        LoadC                                  
        JumpTrue     -print-boolean-56-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-56-join    
        Label        -print-boolean-56-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-56-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        25                        
        Add                                    %% pos6
        LoadC                                  
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
        PushI        26                        
        Add                                    %% pos7
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
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        27                        
        Add                                    %% pos8
        LoadC                                  
        JumpTrue     -print-boolean-59-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-59-join    
        Label        -print-boolean-59-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-59-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        28                        
        Add                                    %% pos9
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
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        29                        
        Add                                    %% pos10
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
        PushD        $global-memory-block      
        PushI        30                        
        Add                                    %% pos11
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
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        31                        
        Add                                    %% pos12
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
        PushI        32                        
        Add                                    %% pos13
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
        PushI        33                        
        Add                                    %% pos14
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
        PushD        $global-memory-block      
        PushI        34                        
        Add                                    %% pos15
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
        Halt                                   
