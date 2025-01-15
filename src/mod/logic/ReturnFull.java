package mod.logic;

import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;
import mindustry.logic.LExecutor;
import mindustry.logic.LStatement;

import static mod.logic.CallFull.CallInstruction.pointers;

public class ReturnFull {
    public static class ReturnInstruction implements LExecutor.LInstruction {

        public ReturnInstruction() {}
        @Override
        public void run(LExecutor lExecutor) {
            if(!pointers.containsKey(lExecutor))pointers.put(lExecutor,new Seq<>());
            if(pointers.get(lExecutor).size>0) lExecutor.var(LExecutor.varCounter).numval=pointers.get(lExecutor).pop();
        }
    }
    public static class ReturnStatement extends LStatement {
        public ReturnStatement() {}
        @Override
        public LCategory category() {
            return LCategory.control;
        }
        @Override
        public void build(Table table) {}
        @Override
        public LExecutor.LInstruction build(LAssembler lAssembler) {
            return new ReturnInstruction();
        }
        public static ReturnStatement read(String[] tokens) {//text code -> instance
            return new ReturnStatement();
        }
        @Override
        public void write(StringBuilder builder) {//instance -> text code
            builder.append("ret");
        }
    }
}
