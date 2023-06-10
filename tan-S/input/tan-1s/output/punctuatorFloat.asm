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
        DataZ        15                        
        Label        $$main                    
        PushD        $global-memory-block      
        PushI        0                         
        Add                                    %% pos
        Label        -compare-2-arg1           
        PushF        -3.300000                 
        Label        -compare-1-arg2           
        Label        -compare-2-arg2           
        PushF        3.300000                  
        Label        -compare-2-sub            
        FSubtract                              
        JumpFZero    -compare-2-false          
        Jump         -compare-2-true           
        Label        -compare-2-true           
        PushI        1                         
        Jump         -compare-2-join           
        Label        -compare-2-false          
        PushI        0                         
        Jump         -compare-2-join           
        Label        -compare-2-join           
        StoreC                                 
        PushD        $global-memory-block      
        PushI        1                         
        Add                                    %% pos2
        Label        -compare-4-arg1           
        PushF        2.200000                  
        Label        -compare-3-arg2           
        Label        -compare-4-arg2           
        PushF        1.567800                  
        Label        -compare-4-sub            
        FSubtract                              
        JumpFPos     -compare-4-true           
        Jump         -compare-4-false          
        Label        -compare-4-true           
        PushI        1                         
        Jump         -compare-4-join           
        Label        -compare-4-false          
        PushI        0                         
        Jump         -compare-4-join           
        Label        -compare-4-join           
        StoreC                                 
        PushD        $global-memory-block      
        PushI        2                         
        Add                                    %% pos3
        Label        -compare-6-arg1           
        PushF        2.200000                  
        Label        -compare-5-arg2           
        Label        -compare-6-arg2           
        PushF        1.500000                  
        Label        -compare-6-sub            
        FSubtract                              
        JumpFNeg     -compare-6-true           
        Jump         -compare-6-false          
        Label        -compare-6-true           
        PushI        1                         
        Jump         -compare-6-join           
        Label        -compare-6-false          
        PushI        0                         
        Jump         -compare-6-join           
        Label        -compare-6-join           
        StoreC                                 
        PushD        $global-memory-block      
        PushI        3                         
        Add                                    %% pos4
        Label        -compare-8-arg1           
        PushF        2.200000                  
        Label        -compare-7-arg2           
        Label        -compare-8-arg2           
        PushF        1.500000                  
        Label        -compare-8-sub            
        FSubtract                              
        Duplicate                              
        JumpFNeg     -compare-8-temp           
        JumpFZero    -compare-8-true           
        Jump         -compare-8-false          
        Label        -compare-8-temp           
        Pop                                    
        Jump         -compare-8-true           
        Label        -compare-8-true           
        PushI        1                         
        Jump         -compare-8-join           
        Label        -compare-8-false          
        PushI        0                         
        Jump         -compare-8-join           
        Label        -compare-8-join           
        StoreC                                 
        PushD        $global-memory-block      
        PushI        4                         
        Add                                    %% pos5
        Label        -compare-10-arg1          
        PushF        2.200000                  
        Label        -compare-9-arg2           
        Label        -compare-10-arg2          
        PushF        1.500000                  
        Label        -compare-10-sub           
        FSubtract                              
        Duplicate                              
        JumpFPos     -compare-10-temp          
        JumpFZero    -compare-10-true          
        Jump         -compare-10-false         
        Label        -compare-10-temp          
        Pop                                    
        Jump         -compare-10-true          
        Label        -compare-10-true          
        PushI        1                         
        Jump         -compare-10-join          
        Label        -compare-10-false         
        PushI        0                         
        Jump         -compare-10-join          
        Label        -compare-10-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% pos6
        Label        -compare-12-arg1          
        PushF        2.200000                  
        Label        -compare-11-arg2          
        Label        -compare-12-arg2          
        PushF        2.200000                  
        Label        -compare-12-sub           
        FSubtract                              
        Duplicate                              
        JumpFPos     -compare-12-temp          
        JumpFZero    -compare-12-true          
        Jump         -compare-12-false         
        Label        -compare-12-temp          
        Pop                                    
        Jump         -compare-12-true          
        Label        -compare-12-true          
        PushI        1                         
        Jump         -compare-12-join          
        Label        -compare-12-false         
        PushI        0                         
        Jump         -compare-12-join          
        Label        -compare-12-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        6                         
        Add                                    %% pos7
        Label        -compare-14-arg1          
        PushF        2.200000                  
        Label        -compare-13-arg2          
        Label        -compare-14-arg2          
        PushF        2.200000                  
        Label        -compare-14-sub           
        FSubtract                              
        Duplicate                              
        JumpFNeg     -compare-14-temp          
        JumpFZero    -compare-14-true          
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
        PushI        7                         
        Add                                    %% pos8
        Label        -compare-16-arg1          
        PushF        2.200000                  
        Label        -compare-15-arg2          
        Label        -compare-16-arg2          
        PushF        2.200000                  
        Label        -compare-16-sub           
        FSubtract                              
        JumpFZero    -compare-16-true          
        Jump         -compare-16-false         
        Label        -compare-16-true          
        PushI        1                         
        Jump         -compare-16-join          
        Label        -compare-16-false         
        PushI        0                         
        Jump         -compare-16-join          
        Label        -compare-16-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        8                         
        Add                                    %% pos9
        Label        -compare-18-arg1          
        PushF        2.200000                  
        Label        -compare-17-arg2          
        Label        -compare-18-arg2          
        PushF        3.300000                  
        Label        -compare-18-sub           
        FSubtract                              
        JumpFZero    -compare-18-true          
        Jump         -compare-18-false         
        Label        -compare-18-true          
        PushI        1                         
        Jump         -compare-18-join          
        Label        -compare-18-false         
        PushI        0                         
        Jump         -compare-18-join          
        Label        -compare-18-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        9                         
        Add                                    %% pos10
        Label        -compare-20-arg1          
        PushF        2.200000                  
        Label        -compare-19-arg2          
        Label        -compare-20-arg2          
        PushF        2.200000                  
        Label        -compare-20-sub           
        FSubtract                              
        JumpFZero    -compare-20-false         
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
        PushI        10                        
        Add                                    %% pos11
        Label        -compare-22-arg1          
        PushF        2.200000                  
        Label        -compare-21-arg2          
        Label        -compare-22-arg2          
        PushF        3.300000                  
        Label        -compare-22-sub           
        FSubtract                              
        JumpFZero    -compare-22-false         
        Jump         -compare-22-true          
        Label        -compare-22-true          
        PushI        1                         
        Jump         -compare-22-join          
        Label        -compare-22-false         
        PushI        0                         
        Jump         -compare-22-join          
        Label        -compare-22-join          
        StoreC                                 
        PushD        $global-memory-block      
        PushI        11                        
        Add                                    %% pos12
        Label        -compare-24-arg1          
        PushI        1                         
        Label        -compare-23-arg2          
        Label        -compare-24-arg2          
        PushI        1                         
        Label        -compare-24-sub           
        Subtract                               
        JumpTrue     -compare-24-true          
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
        PushI        12                        
        Add                                    %% pos13
        Label        -compare-26-arg1          
        PushI        1                         
        Label        -compare-25-arg2          
        Label        -compare-26-arg2          
        PushI        0                         
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
        PushI        13                        
        Add                                    %% pos14
        Label        -compare-28-arg1          
        PushI        1                         
        Label        -compare-27-arg2          
        Label        -compare-28-arg2          
        PushI        1                         
        Label        -compare-28-sub           
        Subtract                               
        JumpFalse    -compare-28-true          
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
        PushI        14                        
        Add                                    %% pos15
        Label        -compare-30-arg1          
        PushI        1                         
        Label        -compare-29-arg2          
        Label        -compare-30-arg2          
        PushI        0                         
        Label        -compare-30-sub           
        Subtract                               
        JumpFalse    -compare-30-true          
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
        PushI        0                         
        Add                                    %% pos
        LoadC                                  
        JumpTrue     -print-boolean-31-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-31-join    
        Label        -print-boolean-31-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-31-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        1                         
        Add                                    %% pos2
        LoadC                                  
        JumpTrue     -print-boolean-32-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-32-join    
        Label        -print-boolean-32-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-32-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        2                         
        Add                                    %% pos3
        LoadC                                  
        JumpTrue     -print-boolean-33-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-33-join    
        Label        -print-boolean-33-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-33-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        3                         
        Add                                    %% pos4
        LoadC                                  
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
        PushD        $global-memory-block      
        PushI        4                         
        Add                                    %% pos5
        LoadC                                  
        JumpTrue     -print-boolean-35-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-35-join    
        Label        -print-boolean-35-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-35-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        5                         
        Add                                    %% pos6
        LoadC                                  
        JumpTrue     -print-boolean-36-true    
        PushD        $boolean-false-string     
        Jump         -print-boolean-36-join    
        Label        -print-boolean-36-true    
        PushD        $boolean-true-string      
        Label        -print-boolean-36-join    
        PushD        $print-format-boolean     
        Printf                                 
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        6                         
        Add                                    %% pos7
        LoadC                                  
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
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        7                         
        Add                                    %% pos8
        LoadC                                  
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
        PushD        $global-memory-block      
        PushI        8                         
        Add                                    %% pos9
        LoadC                                  
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
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        9                         
        Add                                    %% pos10
        LoadC                                  
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
        PushD        $global-memory-block      
        PushI        10                        
        Add                                    %% pos11
        LoadC                                  
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
        PushD        $print-format-newline     
        Printf                                 
        PushD        $global-memory-block      
        PushI        11                        
        Add                                    %% pos12
        LoadC                                  
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
        PushD        $global-memory-block      
        PushI        12                        
        Add                                    %% pos13
        LoadC                                  
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
        PushD        $global-memory-block      
        PushI        13                        
        Add                                    %% pos14
        LoadC                                  
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
        PushD        $global-memory-block      
        PushI        14                        
        Add                                    %% pos15
        LoadC                                  
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
        Halt                                   
