package mod.logic;

import arc.scene.ui.layout.Table;
import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;
import mindustry.logic.LExecutor;
import mindustry.logic.LStatement;
import mod.LCategoryExtended;
import mod.NotOverride;

public class GetClassNameFull {
    //instruction --------------------------------------------------------------------------------
    public static class GetClassNameInstruction implements LExecutor.LInstruction {
        public int src;
        public int result;

        public GetClassNameInstruction() {}
        public GetClassNameInstruction(int result, int src) {
            this.src=src;
            this.result=result;
        }
        @Override
        public void run(LExecutor lExecutor) {
            LExecutor.Var srcVar=lExecutor.var(src);
            if(srcVar.objval==null) {
                lExecutor.setobj(this.result, null);
                return;
            }
            lExecutor.setobj(result,srcVar.objval.getClass().getSimpleName());
        }
    }
    //statement ----------------------------------------------------------------------------------
    public static class GetClassNameStatement extends LStatement {
        public String result;
        public String src;

        public GetClassNameStatement() {
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
            table.add(".getClass()");
        }
        @Override
        public LExecutor.LInstruction build(LAssembler lAssembler) {
            return new GetClassNameInstruction(lAssembler.var(result), lAssembler.var(src));
        }
        @NotOverride()
        public static GetClassNameStatement read(String[] tokens) {//from str
            GetClassNameStatement result=new GetClassNameStatement();
            result.result=tokens[1];
            result.src=tokens[2];
            return result;
        }
        @Override
        public void write(StringBuilder builder) {//to str
            builder.append("class ").append(result).append(" ").append(src);
        }
    }
}
