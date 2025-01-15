package mod.logic;

import arc.scene.ui.layout.Table;
import mindustry.gen.Building;
import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;
import mindustry.logic.LExecutor;
import mindustry.logic.LStatement;
import mindustry.world.blocks.logic.MessageBlock;
import mod.LCategoryExtended;

public class GetTextFull {
    //instruction --------------------------------------------------------------------------------
    public static class GetTextInstruction implements LExecutor.LInstruction {
        public int build;
        public int result;

        public GetTextInstruction() {}
        public GetTextInstruction(int result, int build) {
            this.build=build;
            this.result=result;
        }
        @Override
        public void run(LExecutor lExecutor) {
            int index = lExecutor.numi(build);
            if(index<0||index>=lExecutor.links.length){
                lExecutor.setobj(result,null);
                return;
            }
            Building building = lExecutor.links[index];
            if(building instanceof MessageBlock.MessageBuild){
                lExecutor.setobj(result, ((MessageBlock.MessageBuild) building).config());
                return;
            }
            lExecutor.setobj(result,null);
        }
    }
    //statement ----------------------------------------------------------------------------------
    public static class GetTextStatement extends LStatement {
        public String result;
        public String build;

        public GetTextStatement() {
            this.build="messageN";
            this.result="input?";
        }
        @Override
        public LCategory category() {
            return LCategory.io;
        }
        @Override
        public void build(Table table) {
            rebuild(table);
        }
        void rebuild(Table table) {
            table.clearChildren();
            field(table, result, str -> result=str);
            table.add(" = text in ");
            field(table, build, str -> build=str);
        }
        @Override
        public LExecutor.LInstruction build(LAssembler lAssembler) {
            return new GetTextInstruction(lAssembler.var(result), lAssembler.var(build));
        }
        public static GetTextStatement read(String[] tokens) {//from str
            GetTextStatement result=new GetTextStatement();
            result.result=tokens[1];
            result.build=tokens[2];
            return result;
        }
        @Override
        public void write(StringBuilder builder) {//to str
            builder.append("gettext ").append(result).append(" ").append(build);
        }
    }
}
