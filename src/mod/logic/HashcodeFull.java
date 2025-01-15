package mod.logic;

import arc.scene.ui.layout.Table;
import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;
import mindustry.logic.LExecutor;
import mindustry.logic.LStatement;
import mod.LCategoryExtended;

import java.util.Objects;

public class HashcodeFull {
    //instruction --------------------------------------------------------------------------------
    public static class HashcodeInstruction implements LExecutor.LInstruction {
        public int src;
        public int result;

        public HashcodeInstruction() {}
        public HashcodeInstruction(int result, int src) {
            this.src=src;
            this.result=result;
        }
        @Override
        public void run(LExecutor lExecutor) {
            LExecutor.Var srcVar=lExecutor.var(src);
            lExecutor.setnum(this.result, System.identityHashCode(srcVar.objval));
        }
    }
    //statement ----------------------------------------------------------------------------------
    public static class HashcodeStatement extends LStatement {
        public String result;
        public String src;

        public HashcodeStatement() {
            this.src="source";
            this.result="result";
        }
        @Override
        public LCategory category() {
            return LCategoryExtended.object;
        }
        @Override
        public void build(Table table) {
            rebuild(table);
        }
        void rebuild(Table table) {
            table.clearChildren();
            field(table, result, str -> result=str);
            table.add(" = ");
            field(table, src, str -> src=str);
            table.add(".hashcode()");
        }
        @Override
        public LExecutor.LInstruction build(LAssembler lAssembler) {
            return new HashcodeInstruction(lAssembler.var(result), lAssembler.var(src));
        }
        public static HashcodeStatement read(String[] tokens) {//from str
            HashcodeStatement result=new HashcodeStatement();
            result.result=tokens[1];
            result.src=tokens[2];
            return result;
        }
        @Override
        public void write(StringBuilder builder) {//to str
            builder.append("hash ").append(result).append(" ").append(src);
        }
    }
}
