package ru.rikgela.russianmagic.network

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraftforge.network.NetworkDirection
import net.minecraftforge.network.NetworkRegistry
import net.minecraftforge.network.PacketDistributor
import net.minecraftforge.network.simple.SimpleChannel
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.network.packet.ItemStackSyncS2CPacket


object RMMessages {
    private var INSTANCE: SimpleChannel? = null
    private var packetId = 0
    private fun id(): Int {
        return packetId++
    }

    fun register() {
        val net = NetworkRegistry.ChannelBuilder
            .named(ResourceLocation(MOD_ID, "messages"))
            .networkProtocolVersion { "1.0" }
            .clientAcceptedVersions { s: String? -> true }
            .serverAcceptedVersions { s: String? -> true }
            .simpleChannel()
        INSTANCE = net
        net.messageBuilder(ItemStackSyncS2CPacket::class.java, id(), NetworkDirection.PLAY_TO_CLIENT)
            .decoder { friendlyByteBuf: FriendlyByteBuf -> ItemStackSyncS2CPacket(friendlyByteBuf) }
            .encoder(ItemStackSyncS2CPacket::toBytes)
            .consumerMainThread(ItemStackSyncS2CPacket::handle)
            .add()
        net.messageBuilder(ItemStackSyncS2CPacket::class.java, id(), NetworkDirection.PLAY_TO_CLIENT)
            .decoder { friendlyByteBuf: FriendlyByteBuf -> ItemStackSyncS2CPacket(friendlyByteBuf) }
            .encoder(ItemStackSyncS2CPacket::toBytes)
            .consumerMainThread(ItemStackSyncS2CPacket::handle)
            .add()
    }

    fun <MSG> sendToServer(message: MSG) {
        INSTANCE!!.sendToServer(message)
    }

    fun <MSG> sendToPlayer(message: MSG, player: ServerPlayer?) {
        INSTANCE!!.send(PacketDistributor.PLAYER.with { player }, message)
    }

    fun <MSG> sendToClients(message: MSG) {
        INSTANCE!!.send(PacketDistributor.ALL.noArg(), message)
    }
}