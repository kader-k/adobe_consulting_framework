package com.adobe.consulting.components.tags;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;


public class ActionTEI extends TagExtraInfo {

    public VariableInfo[] getVariableInfo(TagData tagData) {
        String id = tagData.getAttributeString("id");
        String className = tagData.getAttributeString("className");
        return new VariableInfo [] {
            new VariableInfo(id, className, true, VariableInfo.AT_BEGIN)
        };
    }
}
