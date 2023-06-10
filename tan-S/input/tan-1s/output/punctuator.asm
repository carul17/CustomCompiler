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
        DataZ        40                        
        Label        $$main                    
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        PushI        97                        
        StoreC                                 
        PushD        $global-memory-block      
        PushI        1                         
        Add                                    %% mai
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
        PushI        5                         
        Add                                    %% b
        PushI        97                        
        StoreC                                 
        PushD        $global-memory-block      
        PushI        6                         
        Add                                    %% c
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% b
        LoadC                                  
        StoreC                                 
        PushD        $global-memory-block      
        PushI        7                         
        Add                                    %% d
        PushI        97                        
        StoreC                                 
        PushD        $global-memory-block      
        PushI        8                         
        Add                                    %% e
        PushI        100                       
        StoreC                                 
        PushD        $global-memory-block      
        PushI        8                         
        Add                                    %% e
        PushD        $global-memory-block      
        PushI        7                         
        Add                                    %% d
        LoadC                                  
        StoreC                                 
        PushD        $global-memory-block      
        PushI        9                         
        Add                                    %% s
        DLabel       -string-2-abc             
        DataI        3                         
        DataI        9                         
        DataI        3                         
        DataC        97                        %% "abc"
        DataC        98                        
        DataC        99                        
        DataC        0                         
        PushD        -string-2-abc             
        StoreI                                 
        PushD        $global-memory-block      
        PushI        13                        
        Add                                    %% s2
        PushD        $global-memory-block      
        PushI        9                         
        Add                                    %% s
        LoadI                                  
        StoreI                                 
        PushD        $global-memory-block      
        PushI        9                         
        Add                                    %% s
        DLabel       -string-3-bde             
        DataI        3                         
        DataI        9                         
        DataI        3                         
        DataC        98                        %% "bde"
        DataC        100                       
        DataC        101                       
        DataC        0                         
        PushD        -string-3-bde             
        StoreI                                 
        PushD        $global-memory-block      
        PushI        17                        
        Add                                    %% s3
        DLabel       -string-4-abc             
        DataI        3                         
        DataI        9                         
        DataI        3                         
        DataC        97                        %% "abc"
        DataC        98                        
        DataC        99                        
        DataC        0                         
        PushD        -string-4-abc             
        StoreI                                 
        PushD        $global-memory-block      
        PushI        21                        
        Add                                    %% s4
        PushD        $global-memory-block      
        PushI        13                        
        Add                                    %% s2
        LoadI                                  
        StoreI                                 
        PushD        $global-memory-block      
        PushI        25                        
        Add                                    %% thing
        Label        -compare-8-arg1           
        DLabel       -string-5-abc             
        DataI        3                         
        DataI        9                         
        DataI        3                         
        DataC        97                        %% "abc"
        DataC        98                        
        DataC        99                        
        DataC        0                         
        PushD        -string-5-abc             
        Label        -compare-7-arg2           
        Label        -compare-8-arg2           
        DLabel       -string-6-abc             
        DataI        3                         
        DataI        9                         
        DataI        3                         
        DataC        97                        %% "abc"
        DataC        98                        
        DataC        99                        
        DataC        0                         
        PushD        -string-6-abc             
        Label        -compare-8-sub            
        Subtract                               
        JumpFalse    -compare-8-true           
        Jump         -compare-8-false          
        Label        -compare-8-true           
        PushI        1                         
        Jump         -compare-8-join           
        Label        -compare-8-false          
        PushI        0                         
        Jump         -compare-8-join           
        Label        -compare-8-join           
        StoreC                                 
        PushD        $global-memory-block      
        PushI        26                        
        Add                                    %% pos2
        Label        -compare-10-arg1          
        PushI        2                         
        Label        -compare-9-arg2           
        Label        -compare-10-arg2          
        PushI        1                         
        Label        -compare-10-sub           
        Subtract                               
        JumpPos      -compare-10-true          
        Jump         -compare-10-false         
        Label        -compare-10-true          
        PushI        1                         
        Jump         -compare-10-join          
        Label        -compare-10-false         
        PushI        0                         
        Jump         -compare-10-join          
        Label        -compare-10-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        27                        
        Add                                    %% pos3
        Label        -compare-12-arg1          
        PushI        2                         
        Label        -compare-11-arg2          
        Label        -compare-12-arg2          
        PushI        1                         
        Label        -compare-12-sub           
        Subtract                               
        JumpNeg      -compare-12-true          
        Jump         -compare-12-false         
        Label        -compare-12-true          
        PushI        1                         
        Jump         -compare-12-join          
        Label        -compare-12-false         
        PushI        0                         
        Jump         -compare-12-join          
        Label        -compare-12-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        28                        
        Add                                    %% pos4
        Label        -compare-14-arg1          
        PushI        2                         
        Label        -compare-13-arg2          
        Label        -compare-14-arg2          
        PushI        1                         
        Label        -compare-14-sub           
        Subtract                               
        Duplicate                              
        JumpNeg      -compare-14-temp          
        JumpFalse    -compare-14-true          
        Jump         -compare-14-false         
        Label        -compare-14-temp          
        Pop                                    
        Jump         -compare-14-true          
        Label        -compare-14-true          
        PushI        1                         
        Jump         -compare-14-join          
        Label        -compare-14-false         
        PushI        0                         
        Jump         -compare-14-join          
        Label        -compare-14-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        29                        
        Add                                    %% pos5
        Label        -compare-16-arg1          
        PushI        2                         
        Label        -compare-15-arg2          
        Label        -compare-16-arg2          
        PushI        1                         
        Label        -compare-16-sub           
        Subtract                               
        Duplicate                              
        JumpPos      -compare-16-temp          
        JumpFalse    -compare-16-true          
        Jump         -compare-16-false         
        Label        -compare-16-temp          
        Pop                                    
        Jump         -compare-16-true          
        Label        -compare-16-true          
        PushI        1                         
        Jump         -compare-16-join          
        Label        -compare-16-false         
        PushI        0                         
        Jump         -compare-16-join          
        Label        -compare-16-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        30                        
        Add                                    %% pos6
        Label        -compare-18-arg1          
        PushI        2                         
        Label        -compare-17-arg2          
        Label        -compare-18-arg2          
        PushI        2                         
        Label        -compare-18-sub           
        Subtract                               
        Duplicate                              
        JumpPos      -compare-18-temp          
        JumpFalse    -compare-18-true          
        Jump         -compare-18-false         
        Label        -compare-18-temp          
        Pop                                    
        Jump         -compare-18-true          
        Label        -compare-18-true          
        PushI        1                         
        Jump         -compare-18-join          
        Label        -compare-18-false         
        PushI        0                         
        Jump         -compare-18-join          
        Label        -compare-18-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        31                        
        Add                                    %% pos7
        Label        -compare-20-arg1          
        PushI        2                         
        Label        -compare-19-arg2          
        Label        -compare-20-arg2          
        PushI        2                         
        Label        -compare-20-sub           
        Subtract                               
        Duplicate                              
        JumpNeg      -compare-20-temp          
        JumpFalse    -compare-20-true          
        Jump         -compare-20-false         
        Label        -compare-20-temp          
        Pop                                    
        Jump         -compare-20-true          
        Label        -compare-20-true          
        PushI        1                         
        Jump         -compare-20-join          
        Label        -compare-20-false         
        PushI        0                         
        Jump         -compare-20-join          
        Label        -compare-20-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        32                        
        Add                                    %% pos8
        Label        -compare-22-arg1          
        PushI        2                         
        Label        -compare-21-arg2          
        Label        -compare-22-arg2          
        PushI        2                         
        Label        -compare-22-sub           
        Subtract                               
        JumpFalse    -compare-22-true          
        Jump         -compare-22-false         
        Label        -compare-22-true          
        PushI        1                         
        Jump         -compare-22-join          
        Label        -compare-22-false         
        PushI        0                         
        Jump         -compare-22-join          
        Label        -compare-22-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        33                        
        Add                                    %% pos9
        Label        -compare-24-arg1          
        PushI        2                         
        Label        -compare-23-arg2          
        Label        -compare-24-arg2          
        PushI        3                         
        Label        -compare-24-sub           
        Subtract                               
        JumpFalse    -compare-24-true          
        Jump         -compare-24-false         
        Label        -compare-24-true          
        PushI        1                         
        Jump         -compare-24-join          
        Label        -compare-24-false         
        PushI        0                         
        Jump         -compare-24-join          
        Label        -compare-24-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        34                        
        Add                                    %% pos10
        Label        -compare-26-arg1          
        PushI        2                         
        Label        -compare-25-arg2          
        Label        -compare-26-arg2          
        PushI        2                         
        Label        -compare-26-sub           
        Subtract                               
        JumpTrue     -compare-26-true          
        Jump         -compare-26-false         
        Label        -compare-26-true          
        PushI        1                         
        Jump         -compare-26-join          
        Label        -compare-26-false         
        PushI        0                         
        Jump         -compare-26-join          
        Label        -compare-26-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        35                        
        Add                                    %% pos11
        Label        -compare-28-arg1          
        PushI        2                         
        Label        -compare-27-arg2          
        Label        -compare-28-arg2          
        PushI        3                         
        Label        -compare-28-sub           
        Subtract                               
        JumpTrue     -compare-28-true          
        Jump         -compare-28-false         
        Label        -compare-28-true          
        PushI        1                         
        Jump         -compare-28-join          
        Label        -compare-28-false         
        PushI        0                         
        Jump         -compare-28-join          
        Label        -compare-28-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        36                        
        Add                                    %% pos12
        Label        -compare-30-arg1          
        PushI        1                         
        Label        -compare-29-arg2          
        Label        -compare-30-arg2          
        PushI        1                         
        Label        -compare-30-sub           
        Subtract                               
        JumpTrue     -compare-30-true          
        Jump         -compare-30-false         
        Label        -compare-30-true          
        PushI        1                         
        Jump         -compare-30-join          
        Label        -compare-30-false         
        PushI        0                         
        Jump         -compare-30-join          
        Label        -compare-30-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        37                        
        Add                                    %% pos13
        Label        -compare-32-arg1          
        PushI        1                         
        Label        -compare-31-arg2          
        Label        -compare-32-arg2          
        PushI        0                         
        Label        -compare-32-sub           
        Subtract                               
        JumpTrue     -compare-32-true          
        Jump         -compare-32-false         
        Label        -compare-32-true          
        PushI        1                         
        Jump         -compare-32-join          
        Label        -compare-32-false         
        PushI        0                         
        Jump         -compare-32-join          
        Label        -compare-32-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        38                        
        Add                                    %% pos14
        Label        -compare-34-arg1          
        PushI        1                         
        Label        -compare-33-arg2          
        Label        -compare-34-arg2          
        PushI        1                         
        Label        -compare-34-sub           
        Subtract                               
        JumpFalse    -compare-34-true          
        Jump         -compare-34-false         
        Label        -compare-34-true          
        PushI        1                         
        Jump         -compare-34-join          
        Label        -compare-34-false         
        PushI        0                         
        Jump         -compare-34-join          
        Label        -compare-34-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        39                        
        Add                                    %% pos15
        Label        -compare-36-arg1          
        PushI        1                         
        Label        -compare-35-arg2          
        Label        -compare-36-arg2          
        PushI        0                         
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
        StoreC                                 
        Label        -compare-38-arg1          
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        Label        -compare-37-arg2          
        LoadC                                  
        Label        -compare-38-arg2          
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% b
        LoadC                                  
        Label        -compare-38-sub           
        Subtract                               
        JumpFalse    -compare-38-true          
        Jump         -compare-38-false         
        Label        -compare-38-true          
        PushI        1                         
        Jump         -compare-38-join          
        Label        -compare-38-false         
        PushI        0                         
        Jump         -compare-38-join          
        Label        -compare-38-join          
        JumpTrue     -print-boolean-39-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-39-join    
        Label        -print-boolean-39-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-39-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-41-arg1          
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        Label        -compare-40-arg2          
        LoadC                                  
        Label        -compare-41-arg2          
        PushD        $global-memory-block      
        PushI        6                         
        Add                                    %% c
        LoadC                                  
        Label        -compare-41-sub           
        Subtract                               
        JumpFalse    -compare-41-true          
        Jump         -compare-41-false         
        Label        -compare-41-true          
        PushI        1                         
        Jump         -compare-41-join          
        Label        -compare-41-false         
        PushI        0                         
        Jump         -compare-41-join          
        Label        -compare-41-join          
        JumpTrue     -print-boolean-42-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-42-join    
        Label        -print-boolean-42-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-42-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-44-arg1          
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        Label        -compare-43-arg2          
        LoadC                                  
        Label        -compare-44-arg2          
        PushD        $global-memory-block      
        PushI        7                         
        Add                                    %% d
        LoadC                                  
        Label        -compare-44-sub           
        Subtract                               
        JumpFalse    -compare-44-true          
        Jump         -compare-44-false         
        Label        -compare-44-true          
        PushI        1                         
        Jump         -compare-44-join          
        Label        -compare-44-false         
        PushI        0                         
        Jump         -compare-44-join          
        Label        -compare-44-join          
        JumpTrue     -print-boolean-45-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-45-join    
        Label        -print-boolean-45-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-45-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-47-arg1          
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        Label        -compare-46-arg2          
        LoadC                                  
        Label        -compare-47-arg2          
        PushD        $global-memory-block      
        PushI        8                         
        Add                                    %% e
        LoadC                                  
        Label        -compare-47-sub           
        Subtract                               
        JumpFalse    -compare-47-true          
        Jump         -compare-47-false         
        Label        -compare-47-true          
        PushI        1                         
        Jump         -compare-47-join          
        Label        -compare-47-false         
        PushI        0                         
        Jump         -compare-47-join          
        Label        -compare-47-join          
        JumpTrue     -print-boolean-48-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-48-join    
        Label        -print-boolean-48-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-48-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        DLabel       -string-49-strings ////   
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
        PushD        -string-49-strings ////   
        PushI        12                        
        Add                                    
        PushD        $print-format-string      
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-51-arg1          
        PushD        $global-memory-block      
        PushI        9                         
        Add                                    %% s
        Label        -compare-50-arg2          
        LoadI                                  
        Label        -compare-51-arg2          
        PushD        $global-memory-block      
        PushI        13                        
        Add                                    %% s2
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
        PushI        13                        
        Add                                    %% s2
        LoadI                                  
        PushI        12                        
        Add                                    
        PushD        $print-format-string      
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Label        -compare-54-arg1          
        PushD        $global-memory-block      
        PushI        9                         
        Add                                    %% s
        Label        -compare-53-arg2          
        LoadI                                  
        Label        -compare-54-arg2          
        PushD        $global-memory-block      
        PushI        17                        
        Add                                    %% s3
        LoadI                                  
        Label        -compare-54-sub           
        Subtract                               
        JumpFalse    -compare-54-true          
        Jump         -compare-54-false         
        Label        -compare-54-true          
        PushI        1                         
        Jump         -compare-54-join          
        Label        -compare-54-false         
        PushI        0                         
        Jump         -compare-54-join          
        Label        -compare-54-join          
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
        Label        -compare-57-arg1          
        PushD        $global-memory-block      
        PushI        9                         
        Add                                    %% s
        Label        -compare-56-arg2          
        LoadI                                  
        Label        -compare-57-arg2          
        PushD        $global-memory-block      
        PushI        21                        
        Add                                    %% s4
        LoadI                                  
        Label        -compare-57-sub           
        Subtract                               
        JumpFalse    -compare-57-true          
        Jump         -compare-57-false         
        Label        -compare-57-true          
        PushI        1                         
        Jump         -compare-57-join          
        Label        -compare-57-false         
        PushI        0                         
        Jump         -compare-57-join          
        Label        -compare-57-join          
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
        PushD        $global-memory-block      
        PushI        9                         
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
        PushI        21                        
        Add                                    %% s4
        LoadI                                  
        PushI        12                        
        Add                                    
        PushD        $print-format-string      
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        25                        
        Add                                    %% thing
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
        PushI        5                         
        PushD        $print-format-integer     
        Printf                                 
        PushD        $print-format-tab         
        Printf                                 
        DLabel       -string-60-end strings /// 
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
        PushD        -string-60-end strings /// 
        PushI        12                        
        Add                                    
        PushD        $print-format-string      
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        26                        
        Add                                    %% pos2
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
        PushI        27                        
        Add                                    %% pos3
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
        PushI        28                        
        Add                                    %% pos4
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
        PushI        29                        
        Add                                    %% pos5
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
        PushI        30                        
        Add                                    %% pos6
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
        PushI        31                        
        Add                                    %% pos7
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
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        32                        
        Add                                    %% pos8
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
        PushD        $global-memory-block      
        PushI        33                        
        Add                                    %% pos9
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
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        34                        
        Add                                    %% pos10
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
        PushD        $global-memory-block      
        PushI        35                        
        Add                                    %% pos11
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
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        36                        
        Add                                    %% pos12
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
        PushI        37                        
        Add                                    %% pos13
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
        PushI        38                        
        Add                                    %% pos14
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
        PushD        $global-memory-block      
        PushI        39                        
        Add                                    %% pos15
        LoadC                                  
        JumpTrue     -print-boolean-74-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-74-join    
        Label        -print-boolean-74-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-74-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        Halt                                   
