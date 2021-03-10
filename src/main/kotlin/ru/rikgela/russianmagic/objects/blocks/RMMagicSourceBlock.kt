import net.minecraft.block.BlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraft.world.IBlockReader
import net.minecraftforge.fml.RegistryObject
import ru.rikgela.russianmagic.objects.blocks.AbstractRMMagicSourceBlock
import ru.rikgela.russianmagic.tileentity.RMMagicSourcesTileEntity

object RMMagicSourceBlock {
    class RMBasicMagicSource(properties: Properties,
                             val registryObject
                             : RegistryObject<TileEntityType<RMMagicSourcesTileEntity.RMBasicMagicSourceTileEntity>>)
        : AbstractRMMagicSourceBlock(properties) {
        override fun createTileEntity(state: BlockState, world: IBlockReader): TileEntity {
            return registryObject.get().create()!!
        }
    }
}