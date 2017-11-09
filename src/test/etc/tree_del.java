public boolean Delete(int v_key) {
    Tree current_node ;
    Tree parent_node ;
    boolean cont ;
    boolean found ;
    boolean ntb ;
    boolean is_root ;
    int key_aux ;

    current_node = this ;
    parent_node = this ;
    cont = true ;
    found = false ;
    is_root = true ;
    while (cont){
        key_aux = current_node.GetKey(e);
        if (v_key < key_aux)
        	if (current_node.GetHas_Left(e)){
            	parent_node = current_node ;
            	current_node = current_node.GetLeft(e) ;
        	}
        	else 
				cont = false ;
        else 
        	if (key_aux < v_key)
            	if (current_node.GetHas_Right(e)){
            	parent_node = current_node ;
            	current_node = current_node.GetRight(e) ;
            	}
            	else 
					cont = false ;
        	else { 
            	if (is_root) 
            		if (!current_node.GetHas_Right(e) && 
                		!current_node.GetHas_Left(e) )
                		ntb = true ;
            		else 
                		ntb = this.Remove(parent_node,current_node); 
				else 
					ntb = this.Remove(parent_node,current_node);
            		found = true ;
            	cont = false ;
        	}
        	is_root = false ;
    	}
	return found ;
}