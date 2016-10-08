// Generated from /Users/rtorres/Downloads/GUI/src/main/resources/arithmetic.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link arithmeticParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface arithmeticVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link arithmeticParser#equation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquation(arithmeticParser.EquationContext ctx);
	/**
	 * Visit a parse tree produced by {@link arithmeticParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(arithmeticParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link arithmeticParser#multiplyingExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyingExpression(arithmeticParser.MultiplyingExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link arithmeticParser#powExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowExpression(arithmeticParser.PowExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link arithmeticParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(arithmeticParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link arithmeticParser#scientific}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScientific(arithmeticParser.ScientificContext ctx);
	/**
	 * Visit a parse tree produced by {@link arithmeticParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelop(arithmeticParser.RelopContext ctx);
	/**
	 * Visit a parse tree produced by {@link arithmeticParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(arithmeticParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link arithmeticParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(arithmeticParser.VariableContext ctx);
}