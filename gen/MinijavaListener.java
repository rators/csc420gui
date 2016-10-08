// Generated from /Users/rtorres/Downloads/GUI/src/main/resources/Minijava.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MinijavaParser}.
 */
public interface MinijavaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#goal}.
	 * @param ctx the parse tree
	 */
	void enterGoal(MinijavaParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#goal}.
	 * @param ctx the parse tree
	 */
	void exitGoal(MinijavaParser.GoalContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void enterMainClass(MinijavaParser.MainClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void exitMainClass(MinijavaParser.MainClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(MinijavaParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(MinijavaParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(MinijavaParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(MinijavaParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#localDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalDeclaration(MinijavaParser.LocalDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#localDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalDeclaration(MinijavaParser.LocalDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(MinijavaParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(MinijavaParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(MinijavaParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(MinijavaParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(MinijavaParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(MinijavaParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(MinijavaParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(MinijavaParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(MinijavaParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(MinijavaParser.MethodBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MinijavaParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MinijavaParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nestedStatement}
	 * labeled alternative in {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterNestedStatement(MinijavaParser.NestedStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nestedStatement}
	 * labeled alternative in {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitNestedStatement(MinijavaParser.NestedStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifElseStatement}
	 * labeled alternative in {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfElseStatement(MinijavaParser.IfElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifElseStatement}
	 * labeled alternative in {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfElseStatement(MinijavaParser.IfElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MinijavaParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MinijavaParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printStatement}
	 * labeled alternative in {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(MinijavaParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printStatement}
	 * labeled alternative in {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(MinijavaParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableAssignmentStatement}
	 * labeled alternative in {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVariableAssignmentStatement(MinijavaParser.VariableAssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableAssignmentStatement}
	 * labeled alternative in {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVariableAssignmentStatement(MinijavaParser.VariableAssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayAssignmentStatement}
	 * labeled alternative in {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterArrayAssignmentStatement(MinijavaParser.ArrayAssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayAssignmentStatement}
	 * labeled alternative in {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitArrayAssignmentStatement(MinijavaParser.ArrayAssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#ifBlock}.
	 * @param ctx the parse tree
	 */
	void enterIfBlock(MinijavaParser.IfBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#ifBlock}.
	 * @param ctx the parse tree
	 */
	void exitIfBlock(MinijavaParser.IfBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#elseBlock}.
	 * @param ctx the parse tree
	 */
	void enterElseBlock(MinijavaParser.ElseBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#elseBlock}.
	 * @param ctx the parse tree
	 */
	void exitElseBlock(MinijavaParser.ElseBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#whileBlock}.
	 * @param ctx the parse tree
	 */
	void enterWhileBlock(MinijavaParser.WhileBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#whileBlock}.
	 * @param ctx the parse tree
	 */
	void exitWhileBlock(MinijavaParser.WhileBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ltExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLtExpression(MinijavaParser.LtExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ltExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLtExpression(MinijavaParser.LtExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectInstantiationExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterObjectInstantiationExpression(MinijavaParser.ObjectInstantiationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectInstantiationExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitObjectInstantiationExpression(MinijavaParser.ObjectInstantiationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayInstantiationExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayInstantiationExpression(MinijavaParser.ArrayInstantiationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayInstantiationExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayInstantiationExpression(MinijavaParser.ArrayInstantiationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code powExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPowExpression(MinijavaParser.PowExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code powExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPowExpression(MinijavaParser.PowExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(MinijavaParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(MinijavaParser.IdentifierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methodCallExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallExpression(MinijavaParser.MethodCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methodCallExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallExpression(MinijavaParser.MethodCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(MinijavaParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(MinijavaParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanLitExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLitExpression(MinijavaParser.BooleanLitExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanLitExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLitExpression(MinijavaParser.BooleanLitExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(MinijavaParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(MinijavaParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intLitExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIntLitExpression(MinijavaParser.IntLitExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intLitExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIntLitExpression(MinijavaParser.IntLitExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(MinijavaParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(MinijavaParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayAccessExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessExpression(MinijavaParser.ArrayAccessExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayAccessExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessExpression(MinijavaParser.ArrayAccessExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpression(MinijavaParser.AddExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpression(MinijavaParser.AddExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterThisExpression(MinijavaParser.ThisExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitThisExpression(MinijavaParser.ThisExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayLengthExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayLengthExpression(MinijavaParser.ArrayLengthExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayLengthExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayLengthExpression(MinijavaParser.ArrayLengthExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubExpression(MinijavaParser.SubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubExpression(MinijavaParser.SubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulExpression(MinijavaParser.MulExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulExpression}
	 * labeled alternative in {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulExpression(MinijavaParser.MulExpressionContext ctx);
}