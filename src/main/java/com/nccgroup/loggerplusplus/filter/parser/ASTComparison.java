/* Generated By:JJTree: Do not edit this line. ASTComparison.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.nccgroup.loggerplusplus.filter.parser;

import com.nccgroup.loggerplusplus.filter.BooleanOperator;
import com.nccgroup.loggerplusplus.logentry.LogEntryField;
import com.nccgroup.loggerplusplus.logentry.LogManager;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Date;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public
class ASTComparison extends SimpleNode {

  Object left, right;
  BooleanOperator booleanOperator;

  public ASTComparison(int id) {
    super(id);
  }

  public ASTComparison(FilterParser p, int id) {
    super(p, id);
  }

  /** Accept the visitor. **/
  public Object jjtAccept(FilterParserVisitor visitor, VisitorData data) {

    return visitor.visit(this, data);
  }

  public BooleanOperator getBooleanOperator() {
    return booleanOperator;
  }

  public Object getLeft() {
    return left;
  }

  public Object getRight() {
    return right;
  }

  @Override
  public String toString() {
    Class<?> leftClass = left instanceof LogEntryField ? ((LogEntryField) left).getType() : left.getClass();
    Class<?> rightClass = right instanceof LogEntryField ? ((LogEntryField) right).getType() : right.getClass();
    return String.format("ASTComparison[left=%s (%s), op=%s, right=%s (%s)]", left, leftClass, booleanOperator, right, rightClass);
  }

  @Override
  public String getFilterString() {
    return String.format("%s %s %s", convertObjectToString(left), booleanOperator.getLabel(), convertObjectToString(right));
  }

  private String convertObjectToString(Object obj){
    if(obj instanceof Pattern){
      if(booleanOperator == BooleanOperator.MATCHES) return "\"" + obj + "\"";
      else return "/" + obj + "/";
    }else if(obj instanceof String){
      return "\"" + StringEscapeUtils.escapeJava((String) obj) + "\"";
    }else if(obj instanceof Set){
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      sb.append(((Set) obj).stream().map(item -> {
        if(item instanceof String) return "\"" + item + "\"";
        else return String.valueOf(item);
      }).collect(Collectors.joining(", ")));
      sb.append("]");
      return sb.toString();
    }else if(obj instanceof Date){
      return "\"" + LogManager.sdf.format(obj) + "\"";
    }
    return String.valueOf(obj);
  }

}
/* JavaCC - OriginalChecksum=bdaeeba5ee349a11518169d312e524e0 (do not edit this line) */
