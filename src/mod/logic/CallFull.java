package mod.logic;

import arc.scene.ui.layout.Table;
import arc.struct.Seq;

import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;
import mindustry.logic.LExecutor;
import mindustry.logic.LStatement;

import java.util.HashMap;

public class CallFull {
    public static class CallInstruction implements LExecutor.LInstruction {
        public static HashMap<LExecutor, Seq<Integer>> pointers=new HashMap<>();
        public int label;

        public CallInstruction() {}
        public CallInstruction(int label) {
            this.label=label;
        }
        @Override
        public void run(LExecutor lExecutor) {
            if(!pointers.containsKey(lExecutor))pointers.put(lExecutor,new Seq<>());
            pointers.get(lExecutor).add((int) lExecutor.counter.numval);
            lExecutor.var(LExecutor.varCounter).numval=lExecutor.numi(label);
        }
    }
    public static class CallStatement extends LStatement {
        public String label;

        public CallStatement() {
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
            return new CallInstruction(lAssembler.var(label));
        }
        public static CallStatement read(String[] tokens) {//text code -> instance
            CallStatement result=new CallStatement();
            result.label = tokens[1];
            return result;
        }
        @Override
        public void write(StringBuilder builder) {//instance -> text code
            builder.append("call ").append(label);
        }
    }
}
