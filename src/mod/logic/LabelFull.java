package mod.logic;

import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.serialization.Base64Coder;
import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;
import mindustry.logic.LExecutor;
import mindustry.logic.LStatement;

public class LabelFull {
    public static class LabelInstruction implements LExecutor.LInstruction {
        public int label;

        public LabelInstruction() {}
        public LabelInstruction(int label) {
            this.label=label;
        }
        @Override
        public void run(LExecutor lExecutor) {
            lExecutor.setconst(label,lExecutor.counter.numval);
        }
    }
    public static class LabelStatement extends LStatement {
        public String label;

        public LabelStatement() {
            label="\"label"+System.currentTimeMillis()%1000+"\"";
        }
        @Override
        public LCategory category() {
            return LCategory.control;
        }
        @Override
        public void build(Table table) {
           table.field(label,str->label=str);
        }
        @Override
        public LExecutor.LInstruction build(LAssembler lAssembler) {
            return new LabelInstruction(lAssembler.var(label));
        }
        public static LabelStatement read(String[] tokens) {//text code -> instance
            LabelStatement result=new LabelStatement();
            result.label = tokens[1];
            return result;
        }
        @Override
        public void write(StringBuilder builder) {//instance -> text code
            builder.append("label ").append(label);
        }
    }
}
